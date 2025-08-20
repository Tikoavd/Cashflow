package com.cashflow.middleware.api.usecase

import com.cashflow.datastore.api.models.AppLanguage
import kotlinx.coroutines.flow.Flow

interface GetAppLanguageUseCase {
    operator fun invoke(): Flow<AppLanguage>
}
