package com.epilabs.epiguard.models

data class SeizureWithRawData(
    val seizureID: Int,
    val userID: Int,
    val seizureTimestamp: String,
    val seizureType: String?,
    val duration: Int?,
    val description: String?,
    val triggers: String?,
    val medicationTaken: String?,
    val postSeizureSymptoms: String?,
    val notes: String?,
    val rawDataId: Int?,
    val rawDataTimestamp: String?,
    val classificationResult: String?,
    val numberOfClassifiedTimesteps: Int?,
    val predictedClass: String?
)