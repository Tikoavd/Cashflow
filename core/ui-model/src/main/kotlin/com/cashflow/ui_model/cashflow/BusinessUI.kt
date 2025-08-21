package com.cashflow.ui_model.cashflow

import androidx.compose.runtime.Immutable
import com.cashflow.database.entities.BusinessDbo

@Immutable
data class BusinessUI(
    val id: Int = 0,
    val name: String = "",
    val downPay: Int = 0,
    val cost: Int = 0,
    val income: Int = 0,
    val mortgage: Int = 0
) {

    fun toDbo() = BusinessDbo(
        id = id,
        name = name,
        downPay = downPay,
        cost = cost,
        income = income,
        mortgage = mortgage
    )
}

fun BusinessDbo.toUI() = BusinessUI(
    id = id,
    name = name,
    downPay = downPay,
    cost = cost,
    income = income,
    mortgage = mortgage
)

