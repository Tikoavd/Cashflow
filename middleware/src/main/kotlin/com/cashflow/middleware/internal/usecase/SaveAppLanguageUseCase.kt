package com.cashflow.middleware.internal.usecase

import com.cashflow.datastore.api.DataStoreRepository
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.dispatchers.api.DispatchersProvider
import com.cashflow.middleware.api.usecase.SaveAppLanguageUseCase
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
internal class SaveAppLanguageUseCase(
    private val dataStoreRepository: DataStoreRepository,
    private val dispatchers: DispatchersProvider
) : SaveAppLanguageUseCase {
    override fun invoke(language: AppLanguage) =
        dataStoreRepository.saveAppLanguage(language).flowOn(dispatchers.io)
}
