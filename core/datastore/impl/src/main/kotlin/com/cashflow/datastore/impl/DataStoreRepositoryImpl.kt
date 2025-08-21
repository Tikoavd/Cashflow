package com.cashflow.datastore.impl

import com.cashflow.datastore.api.DataStoreRepository
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.datastore.api.models.CashflowDso
import com.cashflow.utils.emitFlow
import com.cashflow.utils.tryGetEnumValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
class DataStoreRepositoryImpl(
    private val dataStoreService: DataStoreService,
    private val json: Json
) : DataStoreRepository {

    override fun getAppLanguage(): Flow<AppLanguage> =
        dataStoreService.getAppLanguage().map { it.tryGetEnumValue() }

    override fun saveAppLanguage(language: AppLanguage) = emitFlow {
        dataStoreService.saveAppLanguage(language.name)
    }

    override fun getCashflow(): Flow<CashflowDso> =
        dataStoreService.getCashflow().map { json.decodeFromString(it) }

    override fun saveCashflow(cashflow: CashflowDso): Flow<Unit> = emitFlow {
        dataStoreService.saveCashflow(json.encodeToString(cashflow))
    }
}
