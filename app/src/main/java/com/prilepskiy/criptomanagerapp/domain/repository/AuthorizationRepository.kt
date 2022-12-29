package com.prilepskiy.criptomanagerapp.domain.repository

import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface AuthorizationRepository {
    fun getUser():String?
    fun setUser(user:String)
    suspend fun regestrationUser(login:String,pass:String,email:String):Boolean
    suspend fun addUser(login:String,pass:String,email:String)
    suspend fun searchUser(login:String): Flow<List<UserEntity>>
    suspend fun getAllUsers(): Flow<List<UserEntity>>
}