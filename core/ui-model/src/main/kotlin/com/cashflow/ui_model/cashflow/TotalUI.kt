package com.cashflow.ui_model.cashflow

import androidx.compose.runtime.Immutable

@Immutable
data class TotalUI(
    val passiveIncome: Int = 0,
    val totalIncome: Int = 0,
    val totalExpense: Int = 0,
    val totalChildExpenses: Int = 0,
    val totalLoan: Int = 0,
    val totalCashflow: Int = 0
)
