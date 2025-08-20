package com.cashflow.network.api.extension

import retrofit2.HttpException

fun Throwable.getErrorCode(): Int? {
    return if (this is HttpException) {
        this.code()
    } else {
        null
    }
}

fun Throwable.getErrorBody() = (this as? HttpException)?.response()?.errorBody()?.string()
