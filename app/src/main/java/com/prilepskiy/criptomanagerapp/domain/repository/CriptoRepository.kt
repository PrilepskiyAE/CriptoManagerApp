package com.prilepskiy.criptomanagerapp.domain.repository

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse

interface CriptoRepository {
    suspend fun getTopCoin():ActionResult<CoinResponse>
}