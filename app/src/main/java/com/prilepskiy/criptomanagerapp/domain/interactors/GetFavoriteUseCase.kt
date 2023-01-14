package com.prilepskiy.criptomanagerapp.domain.interactors

import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface GetFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<CoinFavoriteEntity>>
}