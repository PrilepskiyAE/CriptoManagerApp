package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface GetAllUserUseCase {
    suspend operator fun invoke(): Flow<List<UserEntity>>
}