package com.cashflow.utils

sealed class ErrorException : Throwable() {
    data class NetError(val code: Int, override val message: String) : ErrorException()
    data class ErrorMessage(override val message: String?) : ErrorException()
    data object InternalServerError : ErrorException()
    data object UnknownError : ErrorException()
    data object EmptyList : ErrorException()
}

fun Throwable.toErrorException(): ErrorException =
    this as? ErrorException ?: ErrorException.UnknownError
