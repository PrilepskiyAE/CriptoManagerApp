package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse

interface GetCoinUseCase {
    suspend operator fun invoke(): ActionResult<CoinResponse>
}