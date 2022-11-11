package com.prilepskiy.criptomanagerapp.data.repository

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.dataservice.CriptoApiService
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.data.utils.analyzeResponse
import com.prilepskiy.criptomanagerapp.data.utils.makeApiCall
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository

class CriptoRepositoryImpl(private val criptoApiService: CriptoApiService): CriptoRepository {
    override suspend fun getTopCoin(value:Int): ActionResult<CoinResponse> = makeApiCall {
        analyzeResponse(criptoApiService.getTopCoinsInfo(limit = value))
    }
}