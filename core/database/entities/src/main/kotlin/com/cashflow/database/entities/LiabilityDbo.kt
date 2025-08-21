package com.cashflow.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liabilities")
data class LiabilityDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val cost: Int,
    val payment: Int
)
