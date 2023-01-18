package com.prilepskiy.criptomanagerapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_favorite")
data class CoinFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    val idCoin: String,
    val username:String
    )