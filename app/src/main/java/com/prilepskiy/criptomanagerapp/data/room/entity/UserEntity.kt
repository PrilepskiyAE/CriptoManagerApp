package com.prilepskiy.criptomanagerapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    val username:String,
    val email:String,
    val password:String,
    val favoriteCoinId:String
)