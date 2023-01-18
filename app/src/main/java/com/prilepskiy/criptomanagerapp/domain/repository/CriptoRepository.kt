package com.prilepskiy.criptomanagerapp.domain.repository

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import kotlinx.coroutines.flow.Flow

interface CriptoRepository {
    suspend fun getTopCoin(value:Int):ActionResult<CoinResponse>
    suspend fun getFavoriteCoin(): Flow<List<CoinFavoriteEntity>>
    suspend fun getFavoriteCoinInUser(): Flow<List<CoinFavoriteEntity>>
    suspend fun addCoinFavorite(coin:CoinFavoriteEntity)
    suspend fun deleteCoinFavorite(coinId:String)
}