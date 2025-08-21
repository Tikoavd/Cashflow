package com.cashflow.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cashflow.database.entities.StockDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {

    @Upsert
    suspend fun upsertStock(stock: StockDbo)

    @Query("SELECT * FROM stocks")
    fun getStocks(): Flow<List<StockDbo>>

    @Delete
    suspend fun deleteStock(stock: StockDbo)
}
