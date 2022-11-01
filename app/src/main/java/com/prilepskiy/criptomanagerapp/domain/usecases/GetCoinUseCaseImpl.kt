package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository

class GetCoinUseCaseImpl(private val criptoRepository: CriptoRepository):GetCoinUseCase {
    override suspend fun invoke(): ActionResult<CoinResponse> = criptoRepository.getTopCoin()
}