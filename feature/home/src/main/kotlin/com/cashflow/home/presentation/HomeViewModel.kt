package com.cashflow.home.presentation

import androidx.lifecycle.viewModelScope
import com.cashflow.dispatchers.api.DispatchersProvider
import com.cashflow.home.domain.repository.HomeRepository
import com.cashflow.home.domain.usecase.GetTotalUseCase
import com.cashflow.mvi.MviBaseViewModel
import com.cashflow.home.presentation.mvi.HomeAction
import com.cashflow.home.presentation.mvi.HomeAction.*
import com.cashflow.home.presentation.mvi.HomeEffect
import com.cashflow.home.presentation.mvi.HomeIntent
import com.cashflow.home.presentation.mvi.HomeReducer
import com.cashflow.home.presentation.mvi.HomeState
import com.cashflow.middleware.api.usecase.GetAppLanguageUseCase
import com.cashflow.middleware.api.usecase.SaveAppLanguageUseCase
import com.cashflow.ui_model.language.toModel
import com.cashflow.ui_model.language.toUI
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val saveAppLanguageUseCase: SaveAppLanguageUseCase,
    private val dispatchers: DispatchersProvider,
    getTotalUseCase: GetTotalUseCase,
    getAppLanguageUseCase: GetAppLanguageUseCase,
    reducer: HomeReducer
) : MviBaseViewModel<HomeState, HomeAction, HomeIntent, HomeEffect>(HomeState(), reducer) {

    init {
        homeRepository.getCashflow().onEach {
            onAction(UpdateCashflow(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getBusinesses().onEach {
            onAction(UpdateBusinesses(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getExpenses().onEach {
            onAction(UpdateExpenses(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getLiabilities().onEach {
            onAction(UpdateLiabilities(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getStocks().onEach {
            onAction(UpdateStocks(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        getTotalUseCase().onEach {
            onAction(UpdateTotal(it))
        }.launchIn(viewModelScope)

        viewState.map { it.cashflow }.debounce(500L).onEach {
            homeRepository.updateCashflow(it).flowOn(dispatchers.io).launchIn(viewModelScope)
        }.launchIn(viewModelScope)

        homeRepository.getCurrency().onEach {
            onAction(UpdateCurrency(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getCurrencyList().onEach {
            onAction(UpdateCurrencies(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        getAppLanguageUseCase().onEach {
            onAction(UpdateLanguage(it.toUI()))
        }.launchIn(viewModelScope)
    }

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnChildCountChange ->
                onAction(UpdateChildCount(intent.childCount))

            is HomeIntent.OnSalaryChange ->
                onAction(UpdateSalary(intent.salary))

            is HomeIntent.OnSavingsChange ->
                onAction(UpdateSavings(intent.savings))

            is HomeIntent.OnPerChildExpenseChange ->
                onAction(UpdatePerChildExpense(intent.perChildExpense))

            is HomeIntent.OnDeleteBusiness ->
                homeRepository.deleteBusiness(intent.business).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnDeleteExpense ->
                homeRepository.deleteExpense(intent.expense).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnDeleteLiability ->
                homeRepository.deleteLiability(intent.liability).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnDeleteStock ->
                homeRepository.deleteStock(intent.stock).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnUpsertBusiness ->
                homeRepository.upsertBusiness(intent.business).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnUpsertExpense ->
                homeRepository.upsertExpense(intent.expense).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnUpsertLiability ->
                homeRepository.upsertLiability(intent.liability).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnUpsertStock ->
                homeRepository.upsertStock(intent.stock).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnUpdateCurrency ->
                homeRepository.saveCurrency(intent.currency).flowOn(dispatchers.io)
                    .launchIn(viewModelScope)

            is HomeIntent.OnLanguageClick ->
                saveAppLanguageUseCase(intent.language.toModel()).launchIn(viewModelScope)
        }
    }
}
