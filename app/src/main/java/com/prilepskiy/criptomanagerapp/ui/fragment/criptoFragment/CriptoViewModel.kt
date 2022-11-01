package com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.core.ActionResult
import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CriptoViewModel(private val getCoinUseCase: GetCoinUseCase):BaseViewModel() {
    fun getCoinList() {
        viewModelScope.launch {
            when (val result = getCoinUseCase.invoke()){
                is ActionResult.Success -> {
                    Log.d(TAG, "getMerchantsList->Success: ${result.data}")
                }
                is ActionResult.Error ->{
                    Log.d(TAG, "getMerchantsList->Error: ${result.errors}")
                }
            }
        }
    }
    companion object{
        const val TAG = "CriptoViewModel"
    }
}