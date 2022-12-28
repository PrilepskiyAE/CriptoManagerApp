package com.prilepskiy.criptomanagerapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.prilepskiy.criptomanagerapp.BuildConfig
import com.prilepskiy.criptomanagerapp.data.dataservice.apiservice.ConvertorApiService
import com.prilepskiy.criptomanagerapp.data.dataservice.apiservice.CriptoApiService
import com.prilepskiy.criptomanagerapp.data.dataservice.appservice.PreferenceService
import com.prilepskiy.criptomanagerapp.data.dataservice.appservice.PreferenceServiceImpl
import com.prilepskiy.criptomanagerapp.data.dataservice.appservice.PreferenceServiceImpl.Companion.STORAGE_TOKEN
import com.prilepskiy.criptomanagerapp.data.repository.AuthorizationRepositoryImpl
import com.prilepskiy.criptomanagerapp.data.repository.ConverterRepositoryImpl
import com.prilepskiy.criptomanagerapp.data.repository.CriptoRepositoryImpl
import com.prilepskiy.criptomanagerapp.data.room.user.UserDataBase
import com.prilepskiy.criptomanagerapp.data.utils.HeaderInterceptor
import com.prilepskiy.criptomanagerapp.domain.repository.AuthorizationRepository
import com.prilepskiy.criptomanagerapp.domain.repository.ConverterRepository
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun retrofitService(api_url:String): Retrofit {

          return  Retrofit.Builder()
                .baseUrl(api_url)
                .addConverterFactory(GsonConverterFactory.create())
                .apply {
                    client(
                        OkHttpClient.Builder()
                            .addInterceptor(HeaderInterceptor())
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                            .readTimeout(1, TimeUnit.MINUTES)
                            .connectTimeout(1, TimeUnit.MINUTES)
                            .writeTimeout(1, TimeUnit.MINUTES)
                            .build()
                    )
                }
                .build()
        }

    single<CriptoApiService> {retrofitService(BuildConfig.API_URL_CRIPTO).create(CriptoApiService::class.java) }
    single <ConvertorApiService> {retrofitService(BuildConfig.API_URL_VALUTE).create(ConvertorApiService::class.java)}
}
val repositoryModule = module {
    single<CriptoRepository> { CriptoRepositoryImpl(get()) }
    single<ConverterRepository> { ConverterRepositoryImpl(get()) }
    factory <AuthorizationRepository> { AuthorizationRepositoryImpl(get(),get()) }
}
val databaseModule = module {
    fun provideUserDataBase(application: Application): UserDataBase {
        return Room.databaseBuilder(
            application,
            UserDataBase::class.java,
            "UserDB"
        ).allowMainThreadQueries()
            .build()
    }
    single {provideUserDataBase(androidApplication())}
    single { get<UserDataBase>().userDao }
}
val storageModule= module {
    fun getStorage(application: Application): SharedPreferences {
        return application.getSharedPreferences(STORAGE_TOKEN, Context.MODE_PRIVATE)
    }

    single { getStorage(androidApplication()) }
    single<PreferenceService> { PreferenceServiceImpl(get()) }
    single<SharedPreferences.Editor> { getStorage(androidApplication()).edit() }
}
