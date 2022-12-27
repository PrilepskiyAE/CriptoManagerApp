package com.prilepskiy.criptomanagerapp.domain.repository

interface AuthorizationRepository {
    fun getUser():String
    fun setUser(user:String)
}