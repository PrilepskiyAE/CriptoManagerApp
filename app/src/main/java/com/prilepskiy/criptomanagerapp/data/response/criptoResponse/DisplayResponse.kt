package com.prilepskiy.criptomanagerapp.data.response.criptoResponse

import com.squareup.moshi.Json

data class DisplayResponse(
    @field:Json(name = "USD")
    val usd: USDResponse
)