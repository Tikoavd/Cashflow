package com.cashflow.home.presentation.mvi

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.cashflow.mvi.MviState
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.CashflowUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI
import com.cashflow.ui_model.cashflow.TotalUI
import com.cashflow.ui_model.language.AppLanguageUI

@Immutable
data class HomeState(
    val cashflow: CashflowUI = CashflowUI(),
    val businesses: SnapshotStateList<BusinessUI> = mutableStateListOf(),
    val expenses: SnapshotStateList<ExpenseUI> = mutableStateListOf(),
    val liabilities: SnapshotStateList<LiabilityUI> = mutableStateListOf(),
    val stocks: SnapshotStateList<StockUI> = mutableStateListOf(),
    val total: TotalUI = TotalUI(),
    val currency: String = "",
    val currencies: SnapshotStateList<String> = mutableStateListOf(),
    val appLanguage: AppLanguageUI = AppLanguageUI.EN,
    val isLoading: Boolean = true
) : MviState
