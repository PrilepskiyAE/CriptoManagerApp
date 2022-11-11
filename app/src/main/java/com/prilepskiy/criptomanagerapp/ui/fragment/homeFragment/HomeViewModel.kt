package com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment.ConvertorViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment.CriptoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getValuteListUseCase: GetValuteListUseCase,private val getCoinUseCase: GetCoinUseCase) : BaseViewModel() {

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

    fun getCoinList() {
        viewModelScope.launch {
            when (val result = getCoinUseCase.invoke(10)){
                is ActionResult.Success -> {
                    Log.d(CriptoViewModel.TAG, "getCoinList()->Success: ${result.data}")
                    _coinList.emit(result.data)
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
}