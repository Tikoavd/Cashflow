package com.cashflow.ui_model.cashflow

import androidx.compose.runtime.Immutable
import com.cashflow.datastore.api.models.CashflowDso

@Immutable
data class CashflowUI(
    val salary: Int = 0,
    val childCount: Int = 0,
    val perChildExpense: Int = 0,
    val savings: Int = 0
) {
    fun toDso() = CashflowDso(
        salary = salary,
        childCount = childCount,
        perChildExpense = perChildExpense
    )
}

fun CashflowDso.toUI() = CashflowUI(
    salary = salary,
    childCount = childCount,
    perChildExpense = perChildExpense,
    savings = savings
)
