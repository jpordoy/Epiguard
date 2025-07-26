package com.epilabs.epiguard.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.DataType
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

    init {
        try {
            val assetFileDescriptor = context.assets.openFd("model.tflite")
            val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
            val fileChannel = fileInputStream.channel
            val startOffset = assetFileDescriptor.startOffset
            val declaredLength = assetFileDescriptor.declaredLength
            val modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            interpreter = Interpreter(modelBuffer)
            Log.d("TFLiteVideoHelper", "Model loaded successfully")
        } catch (e: Exception) {
            Log.e("TFLiteVideoHelper", "Failed to load model: ${e.message}", e)
        }
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
            // Validate Uri
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

            // Set data source
            try {
                retriever.setDataSource(context, videoUri)
            } catch (e: Exception) {
                Log.e("TFLiteVideoHelper", "MediaMetadataRetriever failed to set data source: ${e.message}", e)
                onResult(emptyList())
                return
            }

            // Get duration
            val durationMs = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
            Log.d("TFLiteVideoHelper", "Video duration: ${durationMs}ms, interval: ${intervalMs}ms")

            if (durationMs <= 0) {
                Log.e("TFLiteVideoHelper", "Invalid or zero video duration: $durationMs")
                onResult(emptyList())
                return
            }

            // Process frames
            for (timeMs in 0 until durationMs step intervalMs) {
                val bitmap = retriever.getFrameAtTime(timeMs * 1000, MediaMetadataRetriever.OPTION_CLOSEST)
                if (bitmap != null) {
                    val result = runInference(listOf(bitmap))
                    val softmaxed = softmax(result)
                    val predictedIndex = softmaxed.indices.maxByOrNull { softmaxed[it] } ?: 0
                    val label = labels.getOrElse(predictedIndex) { "Unknown" }
                    val confidence = softmaxed.getOrElse(predictedIndex) { 0f }
                    results.add(
                        VideoPredictionResult(
                            timestamp = timeMs,
                            predictedLabel = label,
                            confidence = confidence
                        )
                    )
                    Log.d("TFLiteVideoHelper", "Prediction at ${timeMs}ms: $label ($confidence)")
                    bitmap.recycle()
                } else {
                    Log.w("TFLiteVideoHelper", "No bitmap extracted at ${timeMs}ms")
                }
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
        val inputBuffer = ByteBuffer.allocateDirect(1 * 224 * 224 * 3 * 4)
        inputBuffer.order(ByteOrder.nativeOrder())

        val frame = frames.firstOrNull() ?: return FloatArray(2).also {
            Log.w("TFLiteVideoHelper", "No frame provided for inference")
        }
        var tensorImage = TensorImage(DataType.FLOAT32)
        try {
            tensorImage.load(frame)
            tensorImage = imageProcessor.process(tensorImage)
        } catch (e: Exception) {
            Log.e("TFLiteVideoHelper", "Failed to process image: ${e.message}", e)
            return FloatArray(2)
        }

        val floatBuffer = tensorImage.buffer.asFloatBuffer()
        val flatFrame = FloatArray(224 * 224 * 3)
        floatBuffer.get(flatFrame)

        for (value in flatFrame) {
            inputBuffer.putFloat(value)
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