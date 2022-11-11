package com.prilepskiy.criptomanagerapp.data.dataservice

import com.prilepskiy.criptomanagerapp.data.response.convertorResponse.valuteResponse

import retrofit2.Response
import retrofit2.http.GET

interface ConvertorApiService {
    @GET("daily_json.js")
    suspend  fun getValuteList(): Response<valuteResponse>
}