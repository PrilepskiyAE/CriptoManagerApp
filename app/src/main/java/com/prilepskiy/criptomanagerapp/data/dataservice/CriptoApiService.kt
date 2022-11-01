package com.prilepskiy.criptomanagerapp.data.dataservice

import com.prilepskiy.criptomanagerapp.data.response.criptoResponse.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CriptoApiService {

    @GET("top/totalvolfull")
    suspend  fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): Response<CoinResponse>
    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"

        private const val CURRENCY = "USD"
    }
}