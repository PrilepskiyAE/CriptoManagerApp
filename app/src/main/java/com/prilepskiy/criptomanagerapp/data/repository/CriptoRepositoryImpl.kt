package com.prilepskiy.criptomanagerapp.data.repository

import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.dataservice.apiservice.CriptoApiService
import com.prilepskiy.criptomanagerapp.data.dataservice.appservice.PreferenceService
import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import com.prilepskiy.criptomanagerapp.data.room.coin.CoinFavoriteDataBase
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.data.room.user.UserDataBase
import com.prilepskiy.criptomanagerapp.data.utils.analyzeResponse
import com.prilepskiy.criptomanagerapp.data.utils.makeApiCall
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import kotlinx.coroutines.flow.Flow

class CriptoRepositoryImpl(private val criptoApiService: CriptoApiService,private val preferenceService: PreferenceService,private val coinFavoriteDB: CoinFavoriteDataBase): CriptoRepository {
    override suspend fun getTopCoin(value:Int): ActionResult<CoinResponse> = makeApiCall {
        analyzeResponse(criptoApiService.getTopCoinsInfo(limit = value))
    }

    override suspend fun getFavoriteCoin(): Flow<List<CoinFavoriteEntity>> {
        return coinFavoriteDB.coinFavoriteDao.getCoinFavorite()
    }

    override suspend fun getFavoriteCoinInUser(): Flow<List<CoinFavoriteEntity>> {
        return  coinFavoriteDB.coinFavoriteDao.getCoinFavoriteIsUsers(preferenceService.getToken()?:"")
    }

    override suspend fun addCoinFavorite(coin: CoinFavoriteEntity) {
        return coinFavoriteDB.coinFavoriteDao.insert(coin)
    }

    override suspend fun deleteCoinFavorite(coin: CoinFavoriteEntity) {
        return coinFavoriteDB.coinFavoriteDao.delete(coin)
    }


}