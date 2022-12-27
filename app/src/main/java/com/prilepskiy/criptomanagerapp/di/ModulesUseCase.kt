package com.prilepskiy.criptomanagerapp.di

import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.SetUserUseCase
import com.prilepskiy.criptomanagerapp.domain.usecases.GetCoinUseCaseImpl
import com.prilepskiy.criptomanagerapp.domain.usecases.GetUserUseCaseImpl
import com.prilepskiy.criptomanagerapp.domain.usecases.GetValuteListUseCaseImpl
import com.prilepskiy.criptomanagerapp.domain.usecases.SetUserUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory< GetCoinUseCase>{GetCoinUseCaseImpl(get())}
    factory <GetValuteListUseCase>{GetValuteListUseCaseImpl(get())}
    factory <GetUserUseCase>{ GetUserUseCaseImpl(get()) }
    factory <SetUserUseCase>{ SetUserUseCaseImpl(get()) }
}