package com.prilepskiy.criptomanagerapp.data.response.criptoResponse

import com.squareup.moshi.Json

data class RAWResponse(
    @field:Json(name = "USD")
    val usd: USDResponse
)