package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel

interface GetCoinUseCase {
    suspend operator fun invoke(): ActionResult<List<CoinInfoModel>>
}