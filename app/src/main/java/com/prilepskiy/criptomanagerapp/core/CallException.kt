package com.prilepskiy.criptomanagerapp.core

data class CallException(
    val errorCode: Int,
    val errorMessage: String? = null,
) : Exception()