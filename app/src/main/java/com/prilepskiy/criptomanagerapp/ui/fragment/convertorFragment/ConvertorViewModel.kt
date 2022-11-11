package com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import com.prilepskiy.criptomanagerapp.ui.dialog.ValuteDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConvertorViewModel(private val getValuteListUseCase: GetValuteListUseCase):BaseViewModel() {
    private val _valuteList: MutableStateFlow<valuteResponse?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val valuteList=_valuteList.asStateFlow()


    fun getValuteList() {
        viewModelScope.launch {
            when (val result = getValuteListUseCase.invoke()){
                is ActionResult.Success -> {
                    Log.d(TAG, "getValuteList()->Success: ${result.data}")
                    _valuteList.emit(result.data)
                }
                is ActionResult.Error ->{
                    Log.d(TAG, "getValuteList()->Error: ${result.errors}")
                }
            }
        }
    }
    companion object{
        const val TAG = "ConvertorViewModel"
    }
}