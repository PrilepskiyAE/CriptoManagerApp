package com.prilepskiy.criptomanagerapp.domain.usecases


import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.SearchUserUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchUserUseCaseImpl(private val autch: AuthorizationRepository): SearchUserUseCase {
    override suspend fun invoke(login: String): Flow<List<UserEntity>> = withContext(Dispatchers.IO){
        autch.searchUser(login)
    }
}