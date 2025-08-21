package com.cashflow.ui_model.cashflow

import androidx.compose.runtime.Immutable
import com.cashflow.database.entities.LiabilityDbo

@Immutable
data class LiabilityUI(
    val id: Int = 0,
    val name: String = "",
    val cost: Int = 0,
    val payment: Int = 0
) {
    fun toDbo() = LiabilityDbo(
        id = id,
        name = name,
        cost = cost,
        payment = payment
    )
}

fun LiabilityDbo.toUI() = LiabilityUI(
    id = id,
    name = name,
    cost = cost,
    payment = payment
)
