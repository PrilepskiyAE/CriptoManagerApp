package com.prilepskiy.criptomanagerapp.data.dataservice.appservice

interface PreferenceService {
    fun getToken():String
    fun setToken(token: String)
}