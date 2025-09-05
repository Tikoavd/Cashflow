package com.cashflow.datastore.api.models

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class CashflowDso(
    val salary: Int = 0,
    val childCount: Int = 0,
    val perChildExpense: Int = 0,
    val savings: Int = 0
)
