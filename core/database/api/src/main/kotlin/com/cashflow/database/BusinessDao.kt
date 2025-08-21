package com.cashflow.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.cashflow.database.entities.BusinessDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessDao {

    @Upsert
    suspend fun upsertBusiness(business: BusinessDbo)

    @Query("SELECT * FROM businesses")
    fun getBusinesses(): Flow<List<BusinessDbo>>

    @Delete
    suspend fun deleteBusiness(business: BusinessDbo)
}
