package com.cashflow.home.presentation.mvi

import androidx.compose.runtime.Stable
import com.cashflow.mvi.MviAction
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.CashflowUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI
import com.cashflow.ui_model.cashflow.TotalUI
import com.cashflow.ui_model.language.AppLanguageUI

@Stable
sealed interface HomeAction : MviAction {
    data class UpdateBusinesses(val businesses: List<BusinessUI>): HomeAction
    data class UpdateCashflow(val cashflow: CashflowUI): HomeAction
    data class UpdateSalary(val salary: Int): HomeAction
    data class UpdateChildCount(val childCount: Int): HomeAction
    data class UpdatePerChildExpense(val perChildExpense: Int): HomeAction
    data class UpdateSavings(val savings: Int): HomeAction
    data class UpdateExpenses(val expenses: List<ExpenseUI>): HomeAction
    data class UpdateLiabilities(val liabilities: List<LiabilityUI>): HomeAction
    data class UpdateStocks(val stocks: List<StockUI>): HomeAction
    data class UpdateTotal(val total: TotalUI): HomeAction

    data class UpdateCurrency(val currency: String): HomeAction

    data class UpdateCurrencies(val currencies: List<String>): HomeAction

    data class UpdateLanguage(val appLanguage: AppLanguageUI): HomeAction
}
