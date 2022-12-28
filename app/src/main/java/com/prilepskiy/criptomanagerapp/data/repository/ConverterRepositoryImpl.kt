package com.prilepskiy.criptomanagerapp.data.repository

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.dataservice.apiservice.ConvertorApiService
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.data.utils.analyzeResponse
import com.prilepskiy.criptomanagerapp.data.utils.makeApiCall
import com.prilepskiy.criptomanagerapp.domain.repository.ConverterRepository

class ConverterRepositoryImpl(private val convertorApiService: ConvertorApiService): ConverterRepository {
    override suspend fun getValuteList(): ActionResult<valuteResponse>
        = makeApiCall { analyzeResponse(convertorApiService.getValuteList()) }

}