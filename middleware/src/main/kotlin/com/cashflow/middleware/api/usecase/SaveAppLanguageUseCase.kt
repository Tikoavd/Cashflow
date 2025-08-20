package com.cashflow.middleware.api.usecase

import com.cashflow.datastore.api.models.AppLanguage
import kotlinx.coroutines.flow.Flow

interface SaveAppLanguageUseCase {
    operator fun invoke(language: AppLanguage): Flow<Unit>
}
