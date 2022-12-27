package com.prilepskiy.criptomanagerapp.ui.fragment.profileLoginFragment


import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.di.viewModelModule
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileLoginViewModel(private val setUserUseCase: SetUserUseCase): BaseViewModel() {

    fun login(login: String){
        viewModelScope.launch {
         setUserUseCase.invoke(login)
        }
    }
    fun logout(){
        viewModelScope.launch {
            setUserUseCase.invoke("")
        }
    }
}