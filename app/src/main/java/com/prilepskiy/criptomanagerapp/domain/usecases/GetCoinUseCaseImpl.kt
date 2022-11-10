package com.prilepskiy.criptomanagerapp.domain.usecases

import android.util.Log
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository

class GetCoinUseCaseImpl(private val criptoRepository: CriptoRepository):GetCoinUseCase {
    override suspend fun invoke(): ActionResult<List<CoinInfoModel>> {
        val result:MutableList<CoinInfoModel> = mutableListOf()
        Log.d("tag", "invoke: ")
        return  when(val apiData=criptoRepository.getTopCoin()){
            is ActionResult.Success -> {

                apiData.data.Data.onEach {
                    Log.d("tag", "invoke: ${it.RAW?.USD}")
                  result.add(  CoinInfoModel.from(it))
                }

                ActionResult.Success(result)
            }

            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }

    }
}
}