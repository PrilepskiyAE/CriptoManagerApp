package com.prilepskiy.criptomanagerapp.ui.fragment.profileAuthorizationFragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.domain.interactors.GetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileAuthorizationViewModel(private val setUserUseCase: SetUserUseCase,private val getUserUseCase: GetUserUseCase): BaseViewModel() {

    private val _navigateLogin: MutableStateFlow<Boolean?> by lazy { MutableStateFlow(null) }

    val navigateLogin=_navigateLogin.asStateFlow()

    fun logout(){
        viewModelScope.launch {
            setUserUseCase.invoke("")
        }
    }


    fun isAutorization(){
        viewModelScope.launch {
            _navigateLogin.emit(!getUserUseCase.invoke().isNullOrEmpty())
            Log.d("TAG", "isAutorization: ${navigateLogin.value}")
        }
    }
}