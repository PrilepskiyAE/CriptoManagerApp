package com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.room.entity.CoinFavoriteEntity
import com.prilepskiy.criptomanagerapp.domain.interactors.*
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment.ConvertorViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment.CriptoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val getValuteListUseCase: GetValuteListUseCase,
                    private val getCoinUseCase: GetCoinUseCase,
                    private val updateCoinInfoModelUseCase: UpdateCoinInfoModelUseCase,
                    private val likeUseCase: LikeUseCase,
                    private val dislikeUseCase: DislikeUseCase,
                    private val getUserUseCase: GetUserUseCase,
                    private val getFavoriteUseCase: GetFavoriteUseCase,
                    ) : BaseViewModel() {

    private val _valuteList: MutableStateFlow<List<ValuteModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val valuteList=_valuteList.asStateFlow()
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
        getValuteList()

    }

    fun getCoinList() {
        viewModelScope.launch {
            when (val result = getCoinUseCase.invoke(10)){
                is ActionResult.Success -> {
                    Log.d(CriptoViewModel.TAG, "getCoinList()->Success: ${result.data}")
                    _coinList.emit(result.data)
                    getCoinFavorite()
                }
                is ActionResult.Error ->{
                    Log.d(CriptoViewModel.TAG, "getCoinList()->Error: ${result.errors}")
                }
            }
        }
    }
    fun getValuteList() {
        viewModelScope.launch {
            when (val result = getValuteListUseCase.invoke()){
                is ActionResult.Success -> {
                    Log.d(ConvertorViewModel.TAG, "getValuteList()->Success: ${result.data}")
                    _valuteList.emit(result.data)
                }
                is ActionResult.Error ->{
                    Log.d(ConvertorViewModel.TAG, "getValuteList()->Error: ${result.errors}")
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
                Log.d(CriptoViewModel.TAG, "getCoinList()->Error: $e")
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
                            Log.d("TAGLike", "invoke: like ")
                            likeUseCase.invoke(
                                CoinFavoriteEntity(
                                idCoin = item.idCoin, username = user
                            )
                            )
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
                    Log.d(CriptoViewModel.TAG, "---->Error: ${result.errors}")
                }
            }
        }
    }
}