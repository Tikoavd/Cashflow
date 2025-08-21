package com.cashflow.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val expense: Int
)
