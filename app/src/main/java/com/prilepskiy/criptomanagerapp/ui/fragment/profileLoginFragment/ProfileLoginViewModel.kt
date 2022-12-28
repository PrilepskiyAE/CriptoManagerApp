package com.prilepskiy.criptomanagerapp.ui.fragment.profileLoginFragment


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.di.viewModelModule
import com.prilepskiy.criptomanagerapp.domain.interactors.AddUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileLoginViewModel(private val setUserUseCase: SetUserUseCase,private val addUserUseCase:AddUserUseCase): BaseViewModel() {
    private val _navigateLogin: MutableSharedFlow<Boolean> = MutableSharedFlow()

    val navigateLogin=_navigateLogin.asSharedFlow()
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
    fun regesrationUser(login:String,pass:String,email:String){
        viewModelScope.launch {
            _navigateLogin.tryEmit( addUserUseCase.invoke(login,pass,email))

        }
    }
}