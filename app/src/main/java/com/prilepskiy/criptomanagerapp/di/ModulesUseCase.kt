package com.prilepskiy.criptomanagerapp.di

import com.prilepskiy.criptomanagerapp.domain.interactors.*
import com.prilepskiy.criptomanagerapp.domain.usecases.*
import org.koin.dsl.module

val useCaseModule = module {
    factory< GetCoinUseCase>{GetCoinUseCaseImpl(get())}
    factory <GetValuteListUseCase>{GetValuteListUseCaseImpl(get())}
    factory <GetUserUseCase>{ GetUserUseCaseImpl(get()) }
    factory <SetUserUseCase>{ SetUserUseCaseImpl(get()) }
    factory <AddUserUseCase>{ AddUserUseCaseImpl(get()) }
    factory <GetAllUserUseCase>{ GetAllUserUseCaseImpl(get()) }
    factory <SearchUserUseCase>{ SearchUserUseCaseImpl(get()) }

    factory <DislikeUseCase>{ DislikeUseCaseImpl(get()) }
    factory <LikeUseCase>{ LikeUseCaseImpl(get()) }
    factory <GetFavoriteUseCase>{ GetFavoriteUseCaseImpl(get()) }
    factory <UpdateCoinInfoModelUseCase>{UpdateCoinInfoModelUseCaseImpl(get())}
}