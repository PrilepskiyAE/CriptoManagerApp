package com.prilepskiy.criptomanagerapp.domain.usecases

import android.util.Log
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import kotlinx.coroutines.flow.collectLatest

class GetCoinUseCaseImpl(private val criptoRepository: CriptoRepository):GetCoinUseCase {
    override suspend fun invoke(value:Int): ActionResult<List<CoinInfoModel>> {
        var result:MutableList<CoinInfoModel> = mutableListOf()

        return  when(val apiData=criptoRepository.getTopCoin(value)){
            is ActionResult.Success -> {

                apiData.data.Data.onEach {

                  result.add(  CoinInfoModel.from(it))
                }
                ActionResult.Success(result)
//                favorite.collectLatest { items ->
//                    items.forEach {
//                    var updatedItem = result.find { i->
//                           it.idCoin == i.idCoin
//                       }
//
//                        val index = result.indexOf(updatedItem)
//                        updatedItem=updatedItem?.copy(favorite = true)
//                        result=result.toMutableList().apply {
//                            if (updatedItem != null) {
//                                this[index]=updatedItem
//                            }
//                        }
//
//                    }
//
//
//                }
//                Log.d("TAG99", "invoke: $result ")

            }

            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }

    }
}
}