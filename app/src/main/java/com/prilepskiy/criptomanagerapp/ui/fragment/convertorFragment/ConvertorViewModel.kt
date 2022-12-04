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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    private var _valuteRight:MutableStateFlow<ValuteModel> = MutableStateFlow(
        ValuteModel(
            "RU",
            "R1111111",
            "российский рубль",
            1,
            "099",
            1.00,
            1.00,
            activate = false)
    )
    val valuteRight=_valuteRight.asStateFlow()

    private var _valuteLeft:MutableStateFlow<ValuteModel> =MutableStateFlow(ValuteModel(
        "RU",
        "R1111111",
        "российский рубль",
        1,
        "099",
        1.00,
        1.00,
        activate = false))
    val valuteLeft=_valuteLeft.asStateFlow()

    private var _valuteStringLeft:MutableStateFlow<String> = MutableStateFlow("")
    val valuteStringLeft=_valuteStringLeft.asStateFlow()

   private var _valuteStringRight:MutableStateFlow<String> = MutableStateFlow("")
    val valuteStringRight=_valuteStringRight.asStateFlow()


 fun getStringRight(valute: String){
     try {


       CoroutineScope(Dispatchers.IO).launch {
           if (!valute.isNullOrEmpty())
           _valuteStringRight.emit(valute)
       }
     }catch (e:Exception){

     }
   }
    fun getStringLeft(valute: String){
       try {
           CoroutineScope(Dispatchers.IO).launch {
               if (!valute.isNullOrEmpty())
                   _valuteStringLeft.emit(valute)
           }
       }catch (e:Exception){

       }


    }


    fun getValuteList() {
        viewModelScope.launch {
            when (val result = getValuteListUseCase.invoke()){
                is ActionResult.Success -> {

                    _valuteList.emit(result.data)
                }
                is ActionResult.Error ->{
                    Log.d(TAG, "getValuteList()->Error: ${result.errors}")
                }
            }
        }
    }

    fun updateRight(valute:ValuteModel){
        viewModelScope.launch {
            _valuteRight.emit(valute)
        }
    }
    fun updateLeft(valute:ValuteModel){
        viewModelScope.launch {
            _valuteLeft.emit(valute)
        }
    }




    companion object{
        const val TAG = "ConvertorViewModel"
    }
}