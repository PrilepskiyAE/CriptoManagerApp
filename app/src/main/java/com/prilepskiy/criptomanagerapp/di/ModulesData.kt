package com.prilepskiy.criptomanagerapp.di

import com.prilepskiy.criptomanagerapp.BuildConfig
import com.prilepskiy.criptomanagerapp.data.dataservice.ConvertorApiService
import com.prilepskiy.criptomanagerapp.data.dataservice.CriptoApiService
import com.prilepskiy.criptomanagerapp.data.repository.ConverterRepositoryImpl
import com.prilepskiy.criptomanagerapp.data.repository.CriptoRepositoryImpl
import com.prilepskiy.criptomanagerapp.data.utils.HeaderInterceptor
import com.prilepskiy.criptomanagerapp.domain.repository.ConverterRepository
import com.prilepskiy.criptomanagerapp.domain.repository.CriptoRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
}