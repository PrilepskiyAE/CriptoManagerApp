package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository

class SetUserUseCaseImpl(private val authorizationRepository: AuthorizationRepository): SetUserUseCase {
    override suspend fun invoke(user: String) {
        authorizationRepository.setUser(user)
    }
}