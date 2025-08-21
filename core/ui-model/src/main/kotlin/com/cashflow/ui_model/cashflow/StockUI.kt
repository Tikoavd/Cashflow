package com.cashflow.ui_model.cashflow

import androidx.compose.runtime.Immutable
import com.cashflow.database.entities.StockDbo

@Immutable
data class StockUI(
    val id: Int = 0,
    val name: String = "",
    val quantity: Int = 0,
    val price: Int = 0,
    val income: Int = 0,
    val mortgage: Int = 0
) {
    fun toDbo() = StockDbo(
        id = id,
        name = name,
        quantity = quantity,
        price = price,
        income = income,
        mortgage = mortgage
    )
}

fun StockDbo.toUI() = StockUI(
    id = id,
    name = name,
    quantity = quantity,
    price = price,
    income = income,
    mortgage = mortgage
)
