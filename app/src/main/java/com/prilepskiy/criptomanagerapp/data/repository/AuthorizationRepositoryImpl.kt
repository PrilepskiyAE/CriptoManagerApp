package com.prilepskiy.criptomanagerapp.data.repository

import com.prilepskiy.criptomanagerapp.data.dataservice.appservice.PreferenceService
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl(private val preferenceService: PreferenceService):AuthorizationRepository {
    override fun getUser(): String? {
        return preferenceService.getToken()
    }

    override fun setUser(user:String) {
        preferenceService.setToken(user)
    }
}