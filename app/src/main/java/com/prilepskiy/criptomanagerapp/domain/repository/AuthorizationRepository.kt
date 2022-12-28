package com.prilepskiy.criptomanagerapp.domain.repository

interface AuthorizationRepository {
    fun getUser():String?
    fun setUser(user:String)
    suspend fun regestrationUser(login:String,pass:String,email:String):Boolean
}