package com.prilepskiy.criptomanagerapp.di

import com.prilepskiy.criptomanagerapp.ui.fragment.convertorFragment.ConvertorViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.criptoFragment.CriptoViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.homeFragment.HomeViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.profileAuthorizationFragment.ProfileAuthorizationViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.profileFragment.ProfileViewModel
import com.prilepskiy.criptomanagerapp.ui.fragment.profileLoginFragment.ProfileLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {HomeViewModel(get(),get())}
    viewModel {CriptoViewModel(get())}
    viewModel {ConvertorViewModel(get())}
    viewModel {ProfileViewModel()}
    viewModel {ProfileAuthorizationViewModel()}
    viewModel {ProfileLoginViewModel()}
}