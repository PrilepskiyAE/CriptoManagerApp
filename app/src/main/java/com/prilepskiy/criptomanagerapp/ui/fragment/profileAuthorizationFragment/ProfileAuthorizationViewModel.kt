package com.prilepskiy.criptomanagerapp.ui.fragment.profileAuthorizationFragment

import androidx.lifecycle.viewModelScope
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ProfileAuthorizationViewModel(private val setUserUseCase: SetUserUseCase): BaseViewModel() {
    fun logout(){
        viewModelScope.launch {
            setUserUseCase.invoke("")
        }
    }
}