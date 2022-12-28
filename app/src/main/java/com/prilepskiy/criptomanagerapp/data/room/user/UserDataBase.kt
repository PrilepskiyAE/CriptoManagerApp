package com.prilepskiy.criptomanagerapp.data.room.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity

@Database(
entities = [UserEntity::class] ,
version = 1,
exportSchema = true
)
abstract class UserDataBase: RoomDatabase() {
    abstract val userDao: UserDao
}