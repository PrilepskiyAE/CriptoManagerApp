package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel

interface GetValuteListUseCase {
    suspend operator fun invoke(): ActionResult<valuteResponse>
}