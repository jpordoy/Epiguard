package com.epilabs.epiguard.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import kotlin.math.exp

data class VideoPredictionResult(
    val timestamp: Long,
    val predictedLabel: String,
    val confidence: Float
)

class TFLiteVideoHelper(context: Context) {
    private var interpreter: Interpreter? = null
    private val labels = arrayOf("Not Seizure", "Seizure")
    private val imageProcessor = ImageProcessor.Builder()
        .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
        .add(NormalizeOp(0f, 255f))
        .build()
    private val batchSize = 10 // Model expects a batch of 10 frames

    init {
        try {
            val assetFileDescriptor = context.assets.openFd("model.tflite")
            val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
            val fileChannel = fileInputStream.channel
            val startOffset = assetFileDescriptor.startOffset
            val declaredLength = assetFileDescriptor.declaredLength
            val modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            interpreter = Interpreter(modelBuffer)
            logModelInputShape() // Log model input shape for debugging
            Log.d("TFLiteVideoHelper", "Model loaded successfully")
        } catch (e: Exception) {
            Log.e("TFLiteVideoHelper", "Failed to load model: ${e.message}", e)
        }
    }

    // Log the model's input tensor shape for debugging
    private fun logModelInputShape() {
        interpreter?.let {
            val inputTensor = it.getInputTensor(0)
            Log.d("TFLiteVideoHelper", "Input Tensor Shape: ${inputTensor.shape().contentToString()}")
            Log.d("TFLiteVideoHelper", "Input Tensor Data Type: ${inputTensor.dataType()}")
        } ?: Log.e("TFLiteVideoHelper", "Interpreter not initialized")
    }

    fun classifyVideo(
        context: Context,
        videoUri: Uri,
        intervalMs: Long = 2000L,
        onResult: (List<VideoPredictionResult>) -> Unit
    ) {
        val results = mutableListOf<VideoPredictionResult>()
        val retriever = MediaMetadataRetriever()
        try {
            Log.d("TFLiteVideoHelper", "Processing video URI: $videoUri")
            when (videoUri.scheme) {
                "file" -> {
                    val file = File(videoUri.path ?: "")
                    if (!file.exists() || !file.canRead()) {
                        Log.e("TFLiteVideoHelper", "Video file does not exist or is not readable: ${videoUri.path}")
                        onResult(emptyList())
                        return
                    }
                }
                "content" -> {
                    try {
                        context.contentResolver.openInputStream(videoUri)?.close()
                            ?: run {
                                Log.e("TFLiteVideoHelper", "Cannot open input stream for URI: $videoUri")
                                onResult(emptyList())
                                return
                            }
                    } catch (e: Exception) {
                        Log.e("TFLiteVideoHelper", "Failed to validate content URI: ${e.message}", e)
                        onResult(emptyList())
                        return
                    }
                }
                else -> {
                    Log.e("TFLiteVideoHelper", "Unsupported URI scheme: ${videoUri.scheme}")
                    onResult(emptyList())
                    return
                }
            }

            try {
                retriever.setDataSource(context, videoUri)
            } catch (e: Exception) {
                Log.e("TFLiteVideoHelper", "MediaMetadataRetriever failed to set data source: ${e.message}", e)
                onResult(emptyList())
                return
            }

            val durationMs = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
            Log.d("TFLiteVideoHelper", "Video duration: ${durationMs}ms, interval: ${intervalMs}ms")

            if (durationMs <= 0) {
                Log.e("TFLiteVideoHelper", "Invalid or zero video duration: $durationMs")
                onResult(emptyList())
                return
            }

            // Collect frames in batches of `batchSize` (10)
            val frames = mutableListOf<Bitmap>()
            var currentTimeMs = 0L
            while (currentTimeMs < durationMs) {
                val originalBitmap = retriever.getFrameAtTime(
                    currentTimeMs * 1000,
                    MediaMetadataRetriever.OPTION_CLOSEST
                )
                val bitmap = originalBitmap?.let {
                    if (it.config != Bitmap.Config.ARGB_8888) {
                        it.copy(Bitmap.Config.ARGB_8888, true)
                    } else {
                        it
                    }
                }

                if (bitmap != null) {
                    frames.add(bitmap)
                    Log.d("TFLiteVideoHelper", "Extracted frame at ${currentTimeMs}ms")
                } else {
                    Log.w("TFLiteVideoHelper", "No bitmap extracted at ${currentTimeMs}ms")
                }

                // When we have enough frames or reach the end, run inference
                if (frames.size == batchSize || (currentTimeMs + intervalMs >= durationMs && frames.isNotEmpty())) {
                    val result = runInference(frames)
                    val softmaxed = softmax(result)
                    val predictedIndex = softmaxed.indices.maxByOrNull { softmaxed[it] } ?: 0
                    val label = labels.getOrElse(predictedIndex) { "Unknown" }
                    val confidence = softmaxed.getOrElse(predictedIndex) { 0f }
                    results.add(
                        VideoPredictionResult(
                            timestamp = currentTimeMs,
                            predictedLabel = label,
                            confidence = confidence
                        )
                    )
                    Log.d("TFLiteVideoHelper", "Prediction at ${currentTimeMs}ms: $label ($confidence)")

                    // Recycle bitmaps
                    frames.forEach { frame ->
                        frame.recycle()
                        originalBitmap?.takeIf { it != frame }?.recycle()
                    }
                    frames.clear()
                }

                currentTimeMs += intervalMs
            }

            Log.d("TFLiteVideoHelper", "Classification results: $results")
        } catch (e: Exception) {
            Log.e("TFLiteVideoHelper", "Classification failed: ${e.message}", e)
            onResult(emptyList())
        } finally {
            try {
                retriever.release()
            } catch (e: Exception) {
                Log.w("TFLiteVideoHelper", "Failed to release MediaMetadataRetriever: ${e.message}")
            }
        }
        onResult(results)
    }

    private fun runInference(frames: List<Bitmap>): FloatArray {
        val interpreter = interpreter ?: return FloatArray(2).also {
            Log.e("TFLiteVideoHelper", "Interpreter not initialized")
        }

        // Allocate buffer for batchSize frames (10 * 224 * 224 * 3 * 4 bytes)
        val inputBuffer = ByteBuffer.allocateDirect(batchSize * 224 * 224 * 3 * 4)
        inputBuffer.order(ByteOrder.nativeOrder())

        // Process frames (pad with zeros if fewer than batchSize frames)
        val processedFrames = frames.take(batchSize).map { frame ->
            var tensorImage = TensorImage(DataType.FLOAT32)
            try {
                tensorImage.load(frame)
                tensorImage = imageProcessor.process(tensorImage)
                tensorImage
            } catch (e: Exception) {
                Log.e("TFLiteVideoHelper", "Failed to process image: ${e.message}", e)
                null
            }
        }

        // If we don't have enough frames, pad with zeros
        val paddedFrames = processedFrames + List(batchSize - processedFrames.size) { null }
        if (paddedFrames.any { it == null }) {
            Log.w("TFLiteVideoHelper", "Padding frames with zeros: ${paddedFrames.size}/$batchSize")
        }

        // Fill the input buffer
        for (tensorImage in paddedFrames) {
            val flatFrame = if (tensorImage != null) {
                val floatBuffer = tensorImage.buffer.asFloatBuffer()
                FloatArray(224 * 224 * 3).also { floatBuffer.get(it) }
            } else {
                FloatArray(224 * 224 * 3) { 0f } // Pad with zeros
            }
            for (value in flatFrame) {
                inputBuffer.putFloat(value)
            }
        }

        inputBuffer.rewind()

        val outputArray = Array(1) { FloatArray(2) }
        try {
            interpreter.run(inputBuffer, outputArray)
        } catch (e: Exception) {
            Log.e("TFLiteVideoHelper", "Inference failed: ${e.message}", e)
            return FloatArray(2)
        }
        return outputArray[0]
    }

    private fun softmax(logits: FloatArray): FloatArray {
        val maxLogit = logits.maxOrNull() ?: 0f
        val exps = logits.map { exp((it - maxLogit).toDouble()) }
        val sumExp = exps.sum()
        return exps.map { (it / sumExp).toFloat() }.toFloatArray()
    }

    fun close() {
        interpreter?.close()
        interpreter = null
        Log.d("TFLiteVideoHelper", "Interpreter closed")
    }
}