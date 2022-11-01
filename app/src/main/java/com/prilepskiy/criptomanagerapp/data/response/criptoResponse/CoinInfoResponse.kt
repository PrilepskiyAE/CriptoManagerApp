package com.prilepskiy.criptomanagerapp.data.response.criptoResponse

data class CoinInfoResponse(
    val Algorithm: String,
    val AssetLaunchDate: String,
    val BlockNumber: Int,
    val BlockReward: Double,
    val BlockTime: String,
    val DocumentType: String,
    val FullName: String,
    val Id: String,
    val ImageUrl: String,
    val Internal: String,
    val MaxSupply: Double,
    val Name: String,
    val NetHashesPerSecond: String,
    val ProofType: String,
    val Rating: RatingResponse,
    val Type: Int,
    val Url: String
)