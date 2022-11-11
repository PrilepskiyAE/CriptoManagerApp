package com.prilepskiy.criptomanagerapp.di

import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.interactors.GetValuteListUseCase
import com.prilepskiy.criptomanagerapp.domain.usecases.GetCoinUseCaseImpl
import com.prilepskiy.criptomanagerapp.domain.usecases.GetValuteListUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory< GetCoinUseCase>{GetCoinUseCaseImpl(get())}
    factory <GetValuteListUseCase>{GetValuteListUseCaseImpl(get())}
}