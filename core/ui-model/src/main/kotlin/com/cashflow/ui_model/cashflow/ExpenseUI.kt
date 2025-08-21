package com.cashflow.ui_model.cashflow

import androidx.compose.runtime.Immutable
import com.cashflow.database.entities.ExpenseDbo

@Immutable
data class ExpenseUI(
    val id: Int = 0,
    val name: String = "",
    val expense: Int = 0
) {
    fun toDbo() = ExpenseDbo(
        id = id,
        name = name,
        expense = expense
    )
}

fun ExpenseDbo.toUI() = ExpenseUI(
    id = id,
    name = name,
    expense = expense
)

