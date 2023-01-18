package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.LikeUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import kotlinx.coroutines.flow.Flow

class LikeUseCaseImpl(private val criptoRepository: CriptoRepository): LikeUseCase {
    override suspend fun invoke(coin: CoinFavoriteEntity) {
        criptoRepository.addCoinFavorite(coin)
    }
}