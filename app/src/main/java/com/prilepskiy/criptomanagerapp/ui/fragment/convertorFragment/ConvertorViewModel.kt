package com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.domain.model.valute.ValuteModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import com.prilepskiy.criptomanagerapp.ui.dialog.ValuteDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConvertorViewModel(private val getValuteListUseCase: GetValuteListUseCase):BaseViewModel() {
    private val _valuteList: MutableStateFlow<List<ValuteModel>?> by lazy {
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
    fun updateList(valute: ValuteModel){
        viewModelScope.launch {

            var stationItems = _valuteList.value?: listOf()
            var updatedItem = stationItems.find { it.ID == valute.ID }
            val index = stationItems.indexOf(updatedItem)
            updatedItem = updatedItem?.copy(activate = true)
            stationItems = stationItems.toMutableList().apply {
                if (updatedItem != null) {
                    this[index] = updatedItem
                }
                _valuteList.emit(stationItems)
            }
            Log.d(TAG, "updateList: ${_valuteList.value}")
        }
    }

    companion object{
        const val TAG = "ConvertorViewModel"
    }
}