package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface SearchUserUseCase {
    suspend operator fun invoke(login:String): Flow<List<UserEntity>>
}