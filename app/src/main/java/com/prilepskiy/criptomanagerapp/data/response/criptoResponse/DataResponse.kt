package com.prilepskiy.criptomanagerapp.data.response.criptoResponse

import com.squareup.moshi.Json

data class DataResponse(
    val CoinInfo: CoinInfoResponse?,
    @field:Json(name = "DISPLAY")
    val Display: DisplayResponse?,
    @field:Json(name = "RAW")
    val raw: RAWResponse?
)