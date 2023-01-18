package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel

interface UpdateCoinInfoModelUseCase {
    suspend operator fun invoke(item: CoinInfoModel, storeList: List<CoinInfoModel>): ActionResult<List<CoinInfoModel>>
}