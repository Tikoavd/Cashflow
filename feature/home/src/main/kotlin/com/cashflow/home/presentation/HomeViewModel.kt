package com.cashflow.home.presentation

import androidx.lifecycle.viewModelScope
import com.cashflow.dispatchers.api.DispatchersProvider
import com.cashflow.home.domain.repository.HomeRepository
import com.cashflow.home.domain.usecase.GetTotalUseCase
import com.cashflow.mvi.MviBaseViewModel
import com.cashflow.home.presentation.mvi.HomeAction
import com.cashflow.home.presentation.mvi.HomeEffect
import com.cashflow.home.presentation.mvi.HomeIntent
import com.cashflow.home.presentation.mvi.HomeReducer
import com.cashflow.home.presentation.mvi.HomeState
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val dispatchers: DispatchersProvider,
    getTotalUseCase: GetTotalUseCase,
    reducer: HomeReducer
) : MviBaseViewModel<HomeState, HomeAction, HomeIntent, HomeEffect>(HomeState(), reducer) {

    init {
        homeRepository.getCashflow().onEach {
            onAction(HomeAction.UpdateCashflow(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getBusinesses().onEach {
            onAction(HomeAction.UpdateBusinesses(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getExpenses().onEach {
            onAction(HomeAction.UpdateExpenses(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getLiabilities().onEach {
            onAction(HomeAction.UpdateLiabilities(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        homeRepository.getStocks().onEach {
            onAction(HomeAction.UpdateStocks(it))
        }.flowOn(dispatchers.io).launchIn(viewModelScope)

        getTotalUseCase().onEach {
            onAction(HomeAction.UpdateTotal(it))
        }.launchIn(viewModelScope)

        viewState.map { it.cashflow }.debounce(500L).onEach {
            homeRepository.updateCashflow(it).flowOn(dispatchers.io).launchIn(viewModelScope)
        }.launchIn(viewModelScope)
    }

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnChildCountChange ->
                onAction(HomeAction.UpdateChildCount(intent.childCount))

            is HomeIntent.OnSalaryChange ->
                onAction(HomeAction.UpdateSalary(intent.salary))

            is HomeIntent.OnSavingsChange ->
                onAction(HomeAction.UpdateSavings(intent.savings))

            is HomeIntent.OnPerChildExpenseChange ->
                onAction(HomeAction.UpdatePerChildExpense(intent.perChildExpense))

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
        }
    }
}
