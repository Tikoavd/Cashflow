package com.cashflow.datastore.impl

import com.cashflow.datastore.api.DataStoreRepository
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.utils.emitFlow
import com.cashflow.utils.tryGetEnumValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class DataStoreRepositoryImpl(private val dataStoreService: DataStoreService) :
    DataStoreRepository {

    override fun getAppLanguage(): Flow<AppLanguage> =
        dataStoreService.getAppLanguage().map { it.tryGetEnumValue() }

    override fun saveAppLanguage(language: AppLanguage) = emitFlow {
        dataStoreService.saveAppLanguage(language.name)
    }
}
