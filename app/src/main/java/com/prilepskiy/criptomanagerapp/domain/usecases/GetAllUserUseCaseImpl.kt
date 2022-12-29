package com.prilepskiy.criptomanagerapp.domain.usecases


import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.GetAllUserUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetAllUserUseCaseImpl(private val autch: AuthorizationRepository): GetAllUserUseCase {
    override suspend fun invoke(): Flow<List<UserEntity>> = withContext(Dispatchers.IO){
        autch.getAllUsers()
    }
}