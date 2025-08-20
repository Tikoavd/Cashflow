package com.cashflow.datastore.api

import com.cashflow.datastore.api.models.AppLanguage
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    fun getAppLanguage(): Flow<AppLanguage>
    fun saveAppLanguage(language: AppLanguage): Flow<Unit>
}
