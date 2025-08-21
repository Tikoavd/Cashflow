package com.cashflow.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cashflow.database.BusinessDao
import com.cashflow.database.ExpenseDao
import com.cashflow.database.LiabilityDao
import com.cashflow.database.StockDao
import com.cashflow.database.entities.BusinessDbo
import com.cashflow.database.entities.ExpenseDbo
import com.cashflow.database.entities.LiabilityDbo
import com.cashflow.database.entities.StockDbo

@Database(
    entities = [
        BusinessDbo::class,
        ExpenseDbo::class,
        StockDbo::class,
        LiabilityDbo::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun liabilityDao(): LiabilityDao
    abstract fun stockDao(): StockDao
}
