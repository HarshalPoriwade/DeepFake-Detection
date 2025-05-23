package com.example.deepshield.data.Response

import kotlinx.serialization.Serializable


//{
//    "message": "✅ Prediction Complete!",
//    "prediction": "FAKE",
//    "score": 0.9951669523056518
//}
//{"message":"\u2705 Prediction Complete!","prediction":"FAKE","score":0.9951669523056518}
@Serializable
data class DeepFakeVideoResponse(
    val message: String,
    val prediction: String,
    val score: Double
)