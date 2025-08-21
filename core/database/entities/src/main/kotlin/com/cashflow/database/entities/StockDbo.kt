package com.cashflow.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class StockDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val price: Int,
    val income: Int,
    val mortgage: Int
)
