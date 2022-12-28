package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.domain.interactors.GetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository

class GetUserUseCaseImpl(private val authorizationRepository: AuthorizationRepository): GetUserUseCase {
    override suspend fun invoke(): String? {
        return authorizationRepository.getUser()
    }
}