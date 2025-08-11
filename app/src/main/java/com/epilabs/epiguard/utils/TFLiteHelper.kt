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
import kotlin.math.exp

class TFLiteHelper(context: Context) {

    private var interpreter: Interpreter
    private val frameBuffer = ArrayList<Bitmap>()
    val predictionLogs = mutableListOf<PredictionLog>()
    private var predictionId = 0
    private val dbHandler = DatabaseConnector(context)
    private val labels = arrayOf("Seizure", "Not Seizure")  // Swapped as per previous

    private val imageProcessor = ImageProcessor.Builder()
        .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
        .add(NormalizeOp(0f, 255f))  // Consider changing if model needs different norm (e.g., add mean/std ops)
        .build()

    init {
        val assetFileDescriptor = context.assets.openFd("model.tflite")
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        val modelBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        interpreter = Interpreter(modelBuffer)
    }

    fun addFrameAndPredict(bitmap: Bitmap?, userId: Int, seizureID: Int? = null): FloatArray? {
        if (bitmap == null) {
            Log.e("TFLiteDebug", "Null bitmap skipped")
            return null
        }
        frameBuffer.add(bitmap)
        if (frameBuffer.size < 10) return null

        // Debug: Check if frames are varying (log simple hash of first pixel or size)
        Log.d("TFLiteDebug", "Frame buffer size: ${frameBuffer.size}. Latest bitmap width/height: ${bitmap.width}x${bitmap.height}. Pixel sample: ${bitmap.getPixel(0, 0)}")

        val result = runInference(frameBuffer.toList())
        frameBuffer.removeAt(0)

        // Debug: Log raw logits before softmax
        Log.d("TFLiteDebug", "Raw logits: ${result[0]}, ${result[1]}")

        val softmaxed = softmax(result)
        val reversedSoftmaxed = floatArrayOf(softmaxed[1], softmaxed[0])  // Reverse to match labels

        // Debug: Log after softmax
        Log.d("TFLiteDebug", "Softmaxed probs (reversed): Seizure ${reversedSoftmaxed[0]}, Not Seizure ${reversedSoftmaxed[1]}")

        val predictedIndex = reversedSoftmaxed.indices.maxByOrNull { reversedSoftmaxed[it] } ?: 0
        val label = labels[predictedIndex]
        val confidence = reversedSoftmaxed[predictedIndex]

        val log = PredictionLog(
            id = predictionId++,
            timestamp = System.currentTimeMillis(),
            predictedLabel = label,
            confidence = confidence,
            rawScores = reversedSoftmaxed
        )
        predictionLogs.add(log)

        // Save to tblRawData
        val rawData = RawDataModel(
            rawDataId = 0,
            userId = userId,
            seizureID = seizureID,
            timestamp = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(log.timestamp),
            classificationResult = label,
            numberOfClassifiedTimesteps = 10,
            predictedClass = label
        )
        dbHandler.insertRawData(rawData)

        return reversedSoftmaxed
    }

    private fun runInference(frames: List<Bitmap>): FloatArray {
        val inputBuffer = ByteBuffer.allocateDirect(1 * 10 * 224 * 224 * 3 * 4)
        inputBuffer.order(ByteOrder.nativeOrder())

        for (frame in frames) {
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

        inputBuffer.rewind()

        val outputArray = Array(1) { FloatArray(2) }
        interpreter.run(inputBuffer, outputArray)

        return outputArray[0]
    }

    private fun softmax(logits: FloatArray): FloatArray {
        val maxLogit = logits.maxOrNull() ?: 0f
        val exps = logits.map { exp((it - maxLogit).toDouble()) }
        val sumExp = exps.sum()
        return exps.map { (it / sumExp).toFloat() }.toFloatArray()
    }

    fun close() {
        interpreter.close()
    }
}