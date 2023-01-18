package com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.*
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CriptoViewModel(private val getCoinUseCase: GetCoinUseCase,
                      private val getFavoriteUseCase: GetFavoriteUseCase,
                      private val updateCoinInfoModelUseCase: UpdateCoinInfoModelUseCase,
                      private val likeUseCase: LikeUseCase,
                      private val dislikeUseCase: DislikeUseCase,
                      private val getUserUseCase: GetUserUseCase

                      ):BaseViewModel() {


    private val _coinList: MutableStateFlow<List<CoinInfoModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val coinList=_coinList.asStateFlow()
    var origenalListCoin= mutableListOf<CoinInfoModel>()
    private val _searchList: MutableStateFlow<List<CoinFavoriteEntity>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val searchList=_searchList.asStateFlow()
    var searchListCoin= mutableListOf<CoinFavoriteEntity>()
     var blockerList = true

init {
    getCoinList()

}
    fun getCoinList() {
        viewModelScope.launch {
            when (val result = getCoinUseCase.invoke(100)){
                is ActionResult.Success -> {

                     _coinList.emit(result.data)
                    getCoinFavorite()

                    }


                is ActionResult.Error ->{
                    Log.d(TAG, "getCoinList()->Error: ${result.errors}")
                }
            }


        }

    }
   fun getCoinFavorite(){
        viewModelScope.launch{
            if (blockerList){

            if (!coinList.value.isNullOrEmpty()) {
            origenalListCoin= coinList.value as MutableList<CoinInfoModel>
            }

           getFavoriteUseCase.invoke().collectLatest {
             _searchList.value=it
               it.onEach {
                   searchListCoin.add(it)
               }

         }


        }
        }
    }
    fun isLike(){
        viewModelScope.launch {
        try {

            _searchList.value?.forEach { favorite ->
                val test = origenalListCoin.find { coin ->
                    favorite.idCoin == coin.idCoin
                }

                if (test != null) {
                    val index = origenalListCoin.indexOf(test)
                    origenalListCoin = origenalListCoin.apply {
                        if (index != -1)
                            this[index] = test.copy(favorite = true)

                    }
                }
            }
            if (origenalListCoin.isNotEmpty() ){

                _coinList.emit(origenalListCoin)

            }
        } catch (e: java.util.ConcurrentModificationException) {
            Log.d(TAG, "getCoinList()->Error: $e")
        }
        }
    }
    fun likeDislike(item: CoinInfoModel, list:List<CoinInfoModel>,isNotReadonly: Boolean){
        viewModelScope.launch {
            val user=getUserUseCase.invoke()
            when(val result = updateCoinInfoModelUseCase.invoke(item,list)){
                is ActionResult.Success -> {
                   // _coinList.emit(null)
                    _coinList.emit(result.data)
                    if (!user.isNullOrEmpty()&&isNotReadonly){
                    if (!item.favorite){

                        likeUseCase.invoke(CoinFavoriteEntity(
                            idCoin = item.idCoin, username = user
                        ))
                    }else if (item.favorite)
                    {

                        dislikeUseCase.invoke(
                            CoinFavoriteEntity(
                                idCoin = item.idCoin, username = user
                            )

                        )
                    }
                    }
                }
                is ActionResult.Error ->{
                    Log.d(TAG, "---->Error: ${result.errors}")
                }
            }
        }
    }


    companion object{
        const val TAG = "CriptoViewModel"
    }
}