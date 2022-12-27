package com.prilepskiy.criptomanagerapp.domain.interactors

interface SetUserUseCase {
    suspend operator fun invoke(user:String)
}