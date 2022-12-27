package com.prilepskiy.criptomanagerapp.data.room.user

import androidx.room.Dao
import com.prilepskiy.criptomanagerapp.data.room.BaseDao
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
@Dao
abstract class UserDao: BaseDao<UserEntity>() {
}