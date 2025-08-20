package com.cashflow.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cashflow.database.CashflowDao

/*@Database(
    entities = [

    ],
    version = 1
)*/
abstract class AppDatabase : RoomDatabase() {

    abstract fun cashflowDao(): CashflowDao
}
