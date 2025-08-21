package com.cashflow.home.domain.repository

import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.CashflowUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun updateCashflow(cashflow: CashflowUI): Flow<Unit>

    fun getCashflow(): Flow<CashflowUI>

    fun upsertBusiness(business: BusinessUI): Flow<Unit>

    fun getBusinesses(): Flow<List<BusinessUI>>

    fun deleteBusiness(business: BusinessUI): Flow<Unit>

    fun upsertExpense(expense: ExpenseUI): Flow<Unit>

    fun getExpenses(): Flow<List<ExpenseUI>>

    fun deleteExpense(expense: ExpenseUI): Flow<Unit>

    fun upsertLiability(liability: LiabilityUI): Flow<Unit>

    fun getLiabilities(): Flow<List<LiabilityUI>>

    fun deleteLiability(liability: LiabilityUI): Flow<Unit>

    fun upsertStock(stock: StockUI): Flow<Unit>

    fun getStocks(): Flow<List<StockUI>>

    fun deleteStock(stock: StockUI): Flow<Unit>
}
