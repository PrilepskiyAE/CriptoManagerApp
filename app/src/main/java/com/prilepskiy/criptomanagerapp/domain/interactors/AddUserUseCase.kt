package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel

interface AddUserUseCase {
    suspend operator fun invoke(login:String,pass:String,email:String)
}