package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.GetFavoriteUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteUseCaseImpl(private val criptoRepository: CriptoRepository): GetFavoriteUseCase {
    override suspend fun invoke(): Flow<List<CoinFavoriteEntity>> {


        return criptoRepository.getFavoriteCoinInUser()
    }
}