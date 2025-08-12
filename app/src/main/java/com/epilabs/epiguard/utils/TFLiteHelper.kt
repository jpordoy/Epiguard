package com.epilabs.epiguard.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.epilabs.epiguard.database.DatabaseConnector
import com.epilabs.epiguard.models.PredictionLog
import com.epilabs.epiguard.models.RawDataModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.exp

class TFLiteHelper(context: Context) {

    private var interpreter: Interpreter
    private val frameBuffer = ArrayList<Bitmap>()
    private var firstFrameTime: Long? = null
    val predictionLogs = mutableListOf<PredictionLog>()
    private var predictionId = 0
    private val dbHandler = DatabaseConnector(context)
    private val labels = arrayOf("Seizure", "Not Seizure") // [0: Seizure, 1: Not Seizure]

    private val imageProcessor = ImageProcessor.Builder()
        .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
        .add(NormalizeOp(0f, 255f))
        .build()

    init {
        val assetFileDescriptor = context.assets.openFd("model.tflite")
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        val modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        interpreter = Interpreter(modelBuffer, Interpreter.Options().apply {
            setUseXNNPACK(false) // Disable XNNPACK for dynamic tensors
        })
        fileInputStream.close()
        Log.d("TFLiteDebug", "TFLite interpreter initialized")
    }

    fun addFrameAndPredict(bitmap: Bitmap?, userId: Int, seizureID: Int? = null): FloatArray? {
        if (bitmap == null || bitmap.isRecycled) {
            Log.e("TFLiteDebug", "Null or recycled bitmap skipped")
            return null
        }
        val frameTime = System.currentTimeMillis()
        if (firstFrameTime == null) firstFrameTime = frameTime
        frameBuffer.add(bitmap)
        Log.d("TFLiteDebug", "Frame added. Buffer size: ${frameBuffer.size}, Bitmap: ${bitmap.width}x${bitmap.height}, Timestamp: $frameTime")

        // Check if 10 frames or 5 seconds have passed
        val elapsedTime = frameTime - (firstFrameTime ?: frameTime)
        if (frameBuffer.size < 10 && elapsedTime < 5000) {
            Log.d("TFLiteDebug", "Waiting for 10 frames or 5 seconds, current size: ${frameBuffer.size}, elapsed: ${elapsedTime}ms")
            return null
        }

        Log.d("TFLiteDebug", "Running inference with ${frameBuffer.size} frames")
        val inferenceStartTime = System.currentTimeMillis()
        val result = runInference(frameBuffer.toList())
        frameBuffer.clear()
        firstFrameTime = null // Reset for next batch
        Log.d("TFLiteDebug", "Inference completed in ${System.currentTimeMillis() - inferenceStartTime}ms")

        if (result == null) {
            Log.e("TFLiteDebug", "Inference failed, no result")
            return null
        }

        Log.d("TFLiteDebug", "Raw logits: ${result[0]}, ${result[1]}")
        val softmaxed = softmax(result)
        val reversedSoftmaxed = floatArrayOf(softmaxed[1], softmaxed[0]) // Reverse to match labels

        Log.d("TFLiteDebug", "Softmaxed probs (reversed): Seizure ${reversedSoftmaxed[0]}, Not Seizure ${reversedSoftmaxed[1]}")

        val predictedIndex = reversedSoftmaxed.indices.maxByOrNull { reversedSoftmaxed[it] } ?: 0
        val label = labels[predictedIndex]
        val confidence = reversedSoftmaxed[predictedIndex]

        val timestamp = System.currentTimeMillis()
        val log = PredictionLog(
            id = predictionId++,
            timestamp = timestamp,
            predictedLabel = label,
            confidence = confidence,
            rawScores = reversedSoftmaxed
        )
        predictionLogs.add(log)

        // Format timestamp range
        val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val startTime = formatter.format(timestamp - 5000)
        val endTime = formatter.format(timestamp)
        val timestampRange = "$startTime - $endTime"

        val rawData = RawDataModel(
            rawDataId = 0,
            userId = userId,
            seizureID = seizureID,
            timestamp = timestampRange,
            classificationResult = label,
            numberOfClassifiedTimesteps = frameBuffer.size,
            predictedClass = label
        )
        dbHandler.insertRawData(rawData)
        Log.d("TFLiteDebug", "Saved prediction to DB: $timestampRange, Label: $label, Confidence: $confidence, Frames: ${frameBuffer.size}")

        return reversedSoftmaxed
    }

    private fun runInference(frames: List<Bitmap>): FloatArray? {
        try {
            // Use available frames, pad with zeros if less than 10
            val frameCount = frames.size
            val inputBuffer = ByteBuffer.allocateDirect(1 * 10 * 224 * 224 * 3 * 4) // Allocate for 10 frames
            inputBuffer.order(ByteOrder.nativeOrder())

            // Process available frames
            for (frame in frames.take(10)) {
                if (frame.isRecycled) {
                    Log.e("TFLiteDebug", "Skipping recycled bitmap in inference")
                    continue
                }
                var tensorImage = TensorImage(DataType.FLOAT32)
                tensorImage.load(frame)
                tensorImage = imageProcessor.process(tensorImage)

                val floatBuffer = tensorImage.buffer.asFloatBuffer()
                val flatFrame = FloatArray(224 * 224 * 3)
                floatBuffer.get(flatFrame)

                for (value in flatFrame) {
                    inputBuffer.putFloat(value)
                }
            }

            // Pad with zeros if fewer than 10 frames
            if (frameCount < 10) {
                val paddingSize = (10 - frameCount) * 224 * 224 * 3
                repeat(paddingSize) { inputBuffer.putFloat(0f) }
                Log.w("TFLiteDebug", "Padded input with ${10 - frameCount} zeroed frames")
            }

            inputBuffer.rewind()

            val outputArray = Array(1) { FloatArray(2) }
            interpreter.run(inputBuffer, outputArray)

            // Recycle bitmaps after inference
            frames.forEach { if (!it.isRecycled) it.recycle() }
            return outputArray[0]
        } catch (e: Exception) {
            Log.e("TFLiteDebug", "Inference error: ${e.message}", e)
            frames.forEach { if (!it.isRecycled) it.recycle() }
            return null
        }
    }

    private fun softmax(logits: FloatArray): FloatArray {
        val maxLogit = logits.maxOrNull() ?: 0f
        val exps = logits.map { exp((it - maxLogit).toDouble()) }
        val sumExp = exps.sum()
        return exps.map { (it / sumExp).toFloat() }.toFloatArray()
    }

    fun close() {
        interpreter.close()
        frameBuffer.forEach { if (!it.isRecycled) it.recycle() }
        frameBuffer.clear()
        Log.d("TFLiteDebug", "Interpreter closed")
    }
}