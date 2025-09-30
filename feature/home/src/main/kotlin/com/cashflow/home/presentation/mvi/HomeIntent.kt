package com.cashflow.home.presentation.mvi

import androidx.compose.runtime.Stable
import com.cashflow.mvi.MviIntent
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.CashflowUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI
import com.cashflow.ui_model.cashflow.TotalUI

@Stable
sealed interface HomeIntent : MviIntent {
    data class OnSalaryChange(val salary: Int): HomeIntent
    data class OnChildCountChange(val childCount: Int): HomeIntent
    data class OnPerChildExpenseChange(val perChildExpense: Int): HomeIntent
    data class OnSavingsChange(val savings: Int): HomeIntent
    data class OnUpsertBusiness(val business: BusinessUI): HomeIntent
    data class OnUpsertExpense(val expense: ExpenseUI): HomeIntent
    data class OnUpsertLiability(val liability: LiabilityUI): HomeIntent
    data class OnUpsertStock(val stock: StockUI): HomeIntent
    data class OnDeleteBusiness(val business: BusinessUI): HomeIntent
    data class OnDeleteExpense(val expense: ExpenseUI): HomeIntent
    data class OnDeleteLiability(val liability: LiabilityUI): HomeIntent
    data class OnDeleteStock(val stock: StockUI): HomeIntent

    data class OnUpdateCurrency(val currency: String): HomeIntent
}
