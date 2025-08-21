package com.cashflow.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cashflow.database.entities.LiabilityDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface LiabilityDao {

    @Upsert
    suspend fun upsertLiability(liability: LiabilityDbo)

    @Query("SELECT * FROM liabilities")
    fun getLiabilities(): Flow<List<LiabilityDbo>>

    @Delete
    suspend fun deleteLiability(liability: LiabilityDbo)
}
