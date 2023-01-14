package com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.DislikeUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetFavoriteUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.LikeUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CriptoViewModel(private val getCoinUseCase: GetCoinUseCase,
                      private val likeUseCase: LikeUseCase,
                      private val dislikeUseCase: DislikeUseCase,
                      private val getFavoriteUseCase: GetFavoriteUseCase):BaseViewModel() {
    private val _coinList: MutableStateFlow<List<CoinInfoModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val coinList=_coinList.asStateFlow()
    private val _searchCoinFavorite: MutableStateFlow<List<CoinFavoriteEntity>?> by lazy { MutableStateFlow(null) }
    val searchCoinFavorite: StateFlow<List<CoinFavoriteEntity>?> = _searchCoinFavorite.asStateFlow()

    fun getCoinList() {
        viewModelScope.launch {
            when (val result = getCoinUseCase.invoke(40)){
                is ActionResult.Success -> {
                    _coinList.emit(result.data)
                    getFavoriteUseCase.invoke().collectLatest {
                        _searchCoinFavorite.value=it
                    }
                }
                is ActionResult.Error ->{
                    Log.d(TAG, "getCoinList()->Error: ${result.errors}")
                }
            }
        }
    }
    fun like(coin:CoinFavoriteEntity){
        viewModelScope.launch {
            likeUseCase.invoke(coin)
        }
    }
    fun dislike(coin:CoinFavoriteEntity){
        viewModelScope.launch {
            dislikeUseCase.invoke(coin)
        }
    }
    companion object{
        const val TAG = "CriptoViewModel"
    }
}