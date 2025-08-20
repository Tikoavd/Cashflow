package com.cashflow.network.api.managers

import kotlinx.coroutines.flow.Flow

interface ErrorManager {

    val errors: Flow<String>

    suspend fun sendError(t: Throwable)

    fun parseError(t: Throwable): String
}
