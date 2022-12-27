package com.prilepskiy.criptomanagerapp.domain.interactors

interface GetUserUseCase {
    suspend operator fun invoke():String
}