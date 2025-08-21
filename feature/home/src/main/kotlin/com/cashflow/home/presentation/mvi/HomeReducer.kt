package com.cashflow.home.presentation.mvi

import androidx.compose.runtime.toMutableStateList
import org.koin.core.annotation.Single
import com.cashflow.mvi.Reducer

@Single
class HomeReducer : Reducer<HomeAction, HomeState> {

    override fun reduce(action: HomeAction, state: HomeState): HomeState =
        when (action) {
            is HomeAction.UpdateBusinesses -> state.copy(
                businesses = action.businesses.toMutableStateList()
            )
            is HomeAction.UpdateExpenses -> state.copy(
                expenses = action.expenses.toMutableStateList()
            )
            is HomeAction.UpdateLiabilities -> state.copy(
                liabilities = action.liabilities.toMutableStateList()
            )
            is HomeAction.UpdateStocks -> state.copy(
                stocks = action.stocks.toMutableStateList()
            )
            is HomeAction.UpdateTotal -> state.copy(
                total = action.total
            )

            is HomeAction.UpdateChildCount -> state.copy(
                cashflow = state.cashflow.copy(
                    childCount = action.childCount
                )
            )
            is HomeAction.UpdatePerChildExpense -> state.copy(
                cashflow = state.cashflow.copy(
                    perChildExpense = action.perChildExpense
                )
            )
            is HomeAction.UpdateSalary -> state.copy(
                cashflow = state.cashflow.copy(
                    salary = action.salary
                )
            )
            is HomeAction.UpdateSavings -> state.copy(
                cashflow = state.cashflow.copy(
                    savings = action.savings
                )
            )

            is HomeAction.UpdateCashflow -> state.copy(
                cashflow = action.cashflow
            )
        }
}
