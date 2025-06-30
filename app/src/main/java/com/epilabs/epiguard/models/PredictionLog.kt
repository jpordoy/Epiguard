package com.epilabs.epiguard.models

data class PredictionLog(
    val id: Int,
    val timestamp: Long,
    val predictedLabel: String,
    val confidence: Float,
    val rawScores: FloatArray
)