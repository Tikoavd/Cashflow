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
        repository.getBusinesses().flatMapLatest { businesses ->
            repository.getExpenses().flatMapLatest { expenses ->
                repository.getLiabilities().flatMapLatest { liabilities ->
                    repository.getStocks().map { stocks ->
                        val totalChildExpenses = cashflow.perChildExpense * cashflow.childCount
                        val totalExpense = totalChildExpenses + expenses.sumOf { it.expense } +
                                liabilities.sumOf { it.payment }
                        val passiveIncome =
                            businesses.sumOf { it.income } + stocks.sumOf { it.income }
                        val totalIncome = passiveIncome + cashflow.salary
                        val totalLoan = businesses.sumOf { it.mortgage } +
                                liabilities.sumOf { it.cost } + stocks.sumOf { it.mortgage }
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
