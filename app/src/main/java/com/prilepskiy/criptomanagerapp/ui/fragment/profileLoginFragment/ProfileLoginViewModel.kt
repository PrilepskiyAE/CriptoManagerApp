package com.prilepskiy.criptomanagerapp.ui.fragment.profileLoginFragment


import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.data.room.entity.UserEntity
import com.prilepskiy.criptomanagerapp.di.viewModelModule
import com.prilepskiy.criptomanagerapp.domain.interactors.AddUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SearchUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileLoginViewModel(private val setUserUseCase: SetUserUseCase,
                            private val addUserUseCase:AddUserUseCase,
                            private val searchUserUseCase: SearchUserUseCase
                            ): BaseViewModel() {
    private val _navigateLogin: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val navigateLogin=_navigateLogin.asSharedFlow()

    private val _searchUser: MutableStateFlow<List<UserEntity>?> by lazy { MutableStateFlow(null) }
    val searchUser: StateFlow<List<UserEntity>?> = _searchUser.asStateFlow()

    fun login(login: String){
        viewModelScope.launch {
         setUserUseCase.invoke(login)
            _navigateLogin.tryEmit(true)
        }
    }
    fun logout(){
        viewModelScope.launch {
            setUserUseCase.invoke("")
        }
    }
    fun searchUser(login:String){
        viewModelScope.launch {
            searchUserUseCase.invoke(login).collectLatest {
                _searchUser.value=it
            }
        }
    }
    fun addUser(login:String,pass:String,email:String){
        viewModelScope.launch {
            addUserUseCase.invoke(login,pass,email)
        }
    }
}