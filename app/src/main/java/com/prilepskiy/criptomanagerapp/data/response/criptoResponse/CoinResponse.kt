package com.prilepskiy.criptomanagerapp.data.response.criptoResponse

data class CoinResponse(
    val Data: List<DataResponse>,
    val HasWarning: Boolean,
    val Message: String,
    )