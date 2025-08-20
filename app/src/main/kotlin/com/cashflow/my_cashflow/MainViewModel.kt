package com.cashflow.my_cashflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.middleware.api.usecase.GetAppLanguageUseCase
import com.cashflow.network.api.managers.ErrorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getAppLanguageUseCase: GetAppLanguageUseCase,
    private val errorManager: ErrorManager
) : ViewModel() {
    private val _language = MutableStateFlow(AppLanguage.EN)
    val language = _language.asStateFlow()

    val errors = errorManager.errors

    fun getAppLanguage() {
        viewModelScope.launch {
            _language.emitAll(getAppLanguageUseCase())
        }
    }
}
