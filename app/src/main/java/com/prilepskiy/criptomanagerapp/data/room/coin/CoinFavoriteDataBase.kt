package com.prilepskiy.criptomanagerapp.data.room.coin

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.data.room.user.UserDao

@Database(
    entities = [CoinFavoriteEntity::class] ,
    version = 1,
    exportSchema = true
)
abstract class CoinFavoriteDataBase: RoomDatabase() {
    abstract val coinFavoriteDao: CoinFavoriteDao
}