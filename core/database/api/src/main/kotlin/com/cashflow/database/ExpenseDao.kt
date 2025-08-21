package com.cashflow.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cashflow.database.entities.ExpenseDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Upsert
    suspend fun upsertExpense(expense: ExpenseDbo)

    @Query("SELECT * FROM expenses")
    fun getExpenses(): Flow<List<ExpenseDbo>>

    @Delete
    suspend fun deleteExpense(expense: ExpenseDbo)
}
