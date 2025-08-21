package com.cashflow.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "businesses")
data class BusinessDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val downPay: Int,
    val cost: Int,
    val income: Int,
    val mortgage: Int
)
