package com.cashflow.middleware.internal.usecase

import com.cashflow.datastore.api.DataStoreRepository
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.dispatchers.api.DispatchersProvider
import com.cashflow.middleware.api.usecase.GetAppLanguageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
internal class GetAppLanguageUseCaseImpl(
    private val dataStoreRepository: DataStoreRepository,
    private val dispatchers: DispatchersProvider
) : GetAppLanguageUseCase {
    override fun invoke(): Flow<AppLanguage> =
        dataStoreRepository.getAppLanguage().flowOn(dispatchers.io)
}