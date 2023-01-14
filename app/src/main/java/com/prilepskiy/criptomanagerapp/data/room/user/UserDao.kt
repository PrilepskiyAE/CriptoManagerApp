package com.prilepskiy.criptomanagerapp.data.room.user

import androidx.room.Dao
import androidx.room.Query
import com.prilepskiy.criptomanagerapp.data.room.BaseDao
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao: BaseDao<UserEntity>() {
            @Query("SELECT * FROM users")
            abstract fun getAllUsers(): Flow<List<UserEntity>>
            @Query("SELECT * FROM users WHERE username = :username")
            abstract fun searchUsers(username:String):Flow<List<UserEntity>>
}