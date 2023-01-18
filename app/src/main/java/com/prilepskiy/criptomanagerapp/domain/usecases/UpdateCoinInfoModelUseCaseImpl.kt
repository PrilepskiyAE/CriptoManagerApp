package com.prilepskiy.criptomanagerapp.domain.usecases

import android.util.Log
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.DislikeUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.LikeUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.UpdateCoinInfoModelUseCase

import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel

class UpdateCoinInfoModelUseCaseImpl(private val getUserUseCase: GetUserUseCase): UpdateCoinInfoModelUseCase {
    override suspend fun invoke(
        item: CoinInfoModel,
        storeList: List<CoinInfoModel>
    ): ActionResult<List<CoinInfoModel>> {
        val user=getUserUseCase.invoke()?:""
        if (!user.isNullOrEmpty()){


        var stationItems = storeList

        var updatedItem = stationItems.find { it.idCoin == item.idCoin} ?: return ActionResult.Error(Throwable("Error: Item non fount"))
        val index = stationItems.indexOf(updatedItem)
        updatedItem = updatedItem.copy(favorite = !item.favorite)


        if (index == -1) {
            return ActionResult.Error(Throwable("Error: Index Error"))
        }
        stationItems = stationItems.toMutableList().apply {
            this[index] = updatedItem
        }



        return ActionResult.Success(stationItems)
    }else return ActionResult.Error(Throwable("Error: Authorization"))
    }
}