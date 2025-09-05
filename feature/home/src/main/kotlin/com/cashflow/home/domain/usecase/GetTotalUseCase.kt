package com.cashflow.home.domain.usecase

import com.cashflow.dispatchers.api.DispatchersProvider
import com.cashflow.home.domain.repository.HomeRepository
import com.cashflow.ui_model.cashflow.TotalUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

interface GetTotalUseCase {
    operator fun invoke(): Flow<TotalUI>
}

@Factory
class GetTotalUseCaseImpl(
    private val repository: HomeRepository,
    private val dispatchers: DispatchersProvider
) : GetTotalUseCase {

    override fun invoke(): Flow<TotalUI> = repository.getCashflow().flatMapLatest { cashflow ->
        var passiveIncome = 0
        var totalIncome = cashflow.salary
        val totalChildExpenses = cashflow.perChildExpense * cashflow.childCount
        var totalExpense = totalChildExpenses
        var totalLoan = 0
        repository.getBusinesses().flatMapLatest { businesses ->
            passiveIncome += businesses.sumOf { it.income }
            totalLoan += businesses.sumOf { it.mortgage }
            repository.getExpenses().flatMapLatest { expenses ->
                totalExpense += expenses.sumOf { it.expense }
                repository.getLiabilities().flatMapLatest { liabilities ->
                    totalLoan += liabilities.sumOf { it.cost }
                    totalExpense += liabilities.sumOf { it.payment }
                    repository.getStocks().map { stocks ->
                        passiveIncome += stocks.sumOf { it.income }
                        totalLoan += stocks.sumOf { it.mortgage }
                        totalIncome += passiveIncome
                        TotalUI(
                            passiveIncome = passiveIncome,
                            totalIncome = totalIncome,
                            totalExpense = totalExpense,
                            totalChildExpenses = totalChildExpenses,
                            totalLoan = totalLoan,
                            totalCashflow = totalIncome - totalExpense
                        )
                    }.flowOn(dispatchers.io)
                }.flowOn(dispatchers.io)
            }.flowOn(dispatchers.io)
        }.flowOn(dispatchers.io)
    }.flowOn(dispatchers.io)
}
