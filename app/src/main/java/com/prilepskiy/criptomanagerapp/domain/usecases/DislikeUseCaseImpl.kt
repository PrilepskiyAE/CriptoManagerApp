package com.prilepskiy.criptomanagerapp.domain.usecases

import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.DislikeUseCase
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import kotlinx.coroutines.flow.Flow

class DislikeUseCaseImpl(private val criptoRepository: CriptoRepository): DislikeUseCase {
    override suspend fun invoke(coin: CoinFavoriteEntity){
        criptoRepository.deleteCoinFavorite(coin)
    }
}