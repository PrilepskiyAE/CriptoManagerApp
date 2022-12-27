package com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment

import com.prilepskiy.criptomanagerapp.domain.interactors.GetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.ui.base.BaseViewModel

class ProfileViewModel(private val getUserUseCase: GetUserUseCase,private val setUserUseCase: SetUserUseCase):BaseViewModel() {
}