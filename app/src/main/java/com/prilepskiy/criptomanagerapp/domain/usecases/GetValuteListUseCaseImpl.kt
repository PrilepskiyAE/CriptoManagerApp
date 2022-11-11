package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.ConverterRepository
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository

class GetValuteListUseCaseImpl(private val converterRepository: ConverterRepository): GetValuteListUseCase {
    override suspend fun invoke(): ActionResult<valuteResponse> {
        return converterRepository.getValuteList()
    }
}