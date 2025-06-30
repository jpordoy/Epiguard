package com.epilabs.epiguard.models

data class RawDataModel(
    val rawDataId: Int = 0,
    val userId: Int,
    val seizureID: Int?, // Nullable to allow raw data without a confirmed seizure
    val timestamp: String,
    val classificationResult: String,
    val numberOfClassifiedTimesteps: Int,
    val predictedClass: String
)