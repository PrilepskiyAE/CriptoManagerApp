package com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.domain.interactors.GetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.model.coin.CoinInfoModel
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(private val getUserUseCase: GetUserUseCase):BaseViewModel() {
    private val _navigateLogin: MutableStateFlow<Boolean?> by lazy { MutableStateFlow(null) }

    val navigateLogin=_navigateLogin.asStateFlow()




    fun isAutorization(){
        viewModelScope.launch {
        _navigateLogin.emit(!getUserUseCase.invoke().isNullOrEmpty())
            Log.d("TAG", "isAutorization: ${navigateLogin.value}")
            }
        }
    }

