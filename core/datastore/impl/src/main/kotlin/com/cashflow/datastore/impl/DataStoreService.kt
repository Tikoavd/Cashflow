package com.cashflow.datastore.impl

import kotlinx.coroutines.flow.Flow


interface DataStoreService {
    fun getAppLanguage(): Flow<String>
    suspend fun saveAppLanguage(language: String)
}
