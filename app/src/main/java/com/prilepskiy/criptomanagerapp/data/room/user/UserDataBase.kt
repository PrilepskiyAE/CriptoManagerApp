package com.prilepskiy.criptomanagerapp.data.room.user

import androidx.room.RoomDatabase

abstract class UserDataBase: RoomDatabase() {
    abstract val userDao: UserDao
}