package com.cashflow.home.data.repository

import com.cashflow.database.BusinessDao
import com.cashflow.database.ExpenseDao
import com.cashflow.database.LiabilityDao
import com.cashflow.database.StockDao
import com.cashflow.datastore.api.DataStoreRepository
import com.cashflow.home.domain.repository.HomeRepository
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.CashflowUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI
import com.cashflow.ui_model.cashflow.toUI
import com.cashflow.utils.emitFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single
class HomeRepositoryImpl(
    private val dataStoreRepository: DataStoreRepository,
    private val businessDao: BusinessDao,
    private val expenseDao: ExpenseDao,
    private val liabilityDao: LiabilityDao,
    private val stockDao: StockDao
) : HomeRepository {

    override fun updateCashflow(cashflow: CashflowUI): Flow<Unit> =
        dataStoreRepository.saveCashflow(cashflow.toDso())

    override fun getCashflow(): Flow<CashflowUI> =
        dataStoreRepository.getCashflow().map { it.toUI() }

    override fun upsertBusiness(business: BusinessUI): Flow<Unit> = emitFlow {
        businessDao.upsertBusiness(business.toDbo())
    }

    override fun getBusinesses(): Flow<List<BusinessUI>> =
        businessDao.getBusinesses().map { list -> list.map { it.toUI() } }

    override fun deleteBusiness(business: BusinessUI): Flow<Unit> = emitFlow {
        businessDao.deleteBusiness(business.toDbo())
    }

    override fun upsertExpense(expense: ExpenseUI): Flow<Unit> = emitFlow {
        expenseDao.upsertExpense(expense.toDbo())
    }

    override fun getExpenses(): Flow<List<ExpenseUI>> =
        expenseDao.getExpenses().map { list -> list.map { it.toUI() } }

    override fun deleteExpense(expense: ExpenseUI): Flow<Unit> = emitFlow {
        expenseDao.deleteExpense(expense.toDbo())
    }

    override fun upsertLiability(liability: LiabilityUI): Flow<Unit> = emitFlow {
        liabilityDao.upsertLiability(liability.toDbo())
    }

    override fun getLiabilities(): Flow<List<LiabilityUI>> =
        liabilityDao.getLiabilities().map { list -> list.map { it.toUI() } }

    override fun deleteLiability(liability: LiabilityUI): Flow<Unit> = emitFlow {
        liabilityDao.deleteLiability(liability.toDbo())
    }

    override fun upsertStock(stock: StockUI): Flow<Unit> = emitFlow {
        stockDao.upsertStock(stock.toDbo())
    }

    override fun getStocks(): Flow<List<StockUI>> =
        stockDao.getStocks().map { list -> list.map { it.toUI() } }

    override fun deleteStock(stock: StockUI): Flow<Unit> = emitFlow {
        stockDao.deleteStock(stock.toDbo())
    }
}
