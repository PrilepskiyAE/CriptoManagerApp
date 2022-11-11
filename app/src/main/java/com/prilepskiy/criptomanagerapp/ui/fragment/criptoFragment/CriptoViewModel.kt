package com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CriptoViewModel(private val getCoinUseCase: GetCoinUseCase):BaseViewModel() {
    private val _coinList: MutableStateFlow<List<CoinInfoModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val coinList=_coinList.asStateFlow()


    fun getCoinList() {
        viewModelScope.launch {
            when (val result = getCoinUseCase.invoke()){
                is ActionResult.Success -> {
                    Log.d(TAG, "getCoinList()->Success: ${result.data}")
                    _coinList.emit(result.data)
                }
                is ActionResult.Error ->{
                    Log.d(TAG, "getCoinList()->Error: ${result.errors}")
                }
            }
        }
    }
    companion object{
        const val TAG = "CriptoViewModel"
    }
}