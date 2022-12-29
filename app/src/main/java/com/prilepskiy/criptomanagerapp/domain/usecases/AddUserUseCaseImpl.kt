package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.domain.interactors.AddUserUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository

class AddUserUseCaseImpl(private val autch:AuthorizationRepository): AddUserUseCase {
    override suspend fun invoke(login: String, pass: String, email: String) {
        return  autch.addUser(login, pass, email)
    }
}