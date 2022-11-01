package com.prilepskiy.criptomanagerapp.di

import com.prilepskiy.criptomanagerapp.domain.interactors.GetCoinUseCase
import com.prilepskiy.criptomanagerapp.domain.usecases.GetCoinUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory< GetCoinUseCase>{GetCoinUseCaseImpl(get())}
}