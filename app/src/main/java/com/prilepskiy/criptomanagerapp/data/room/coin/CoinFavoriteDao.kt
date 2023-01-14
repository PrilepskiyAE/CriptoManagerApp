package com.prilepskiy.criptomanagerapp.data.room.coin

import androidx.room.Dao
import androidx.room.Query
import com.prilepskiy.criptomanagerapp.data.room.BaseDao
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CoinFavoriteDao: BaseDao<CoinFavoriteEntity>() {
    @Query("SELECT * FROM coin_favorite WHERE username = :username")
    abstract fun getCoinFavoriteIsUsers(username:String): Flow<List<CoinFavoriteEntity>>

    @Query("SELECT * FROM coin_favorite")
    abstract fun getCoinFavorite(): Flow<List<CoinFavoriteEntity>>
}