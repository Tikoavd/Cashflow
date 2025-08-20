package com.cashflow.network.entities.error


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorDto(
    @SerialName("error-code")
    val errorCode: Int? = null,
    @SerialName("message")
    val message: String? = null
)