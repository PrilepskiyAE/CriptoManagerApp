package com.prilepskiy.criptomanagerapp.domain.repository

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse

interface ConverterRepository {
    suspend fun getValuteList(): ActionResult<valuteResponse>
}