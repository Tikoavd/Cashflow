package com.cashflow.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cashflow.ui.R
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI

@Composable
fun ExpensesComponent(
    modifier: Modifier = Modifier,
    childExpenses: Int,
    currency: String,
    expenses: SnapshotStateList<ExpenseUI>,
    liabilities: SnapshotStateList<LiabilityUI>,
    onUpsertExpense: (expense: ExpenseUI) -> Unit,
    onDeleteExpense: (expense: ExpenseUI) -> Unit
) {
    var editExpense by remember { mutableStateOf<ExpenseUI?>(null) }

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.onBackground)
                .padding(horizontal = 8.dp),
            text = stringResource(R.string.expenses),
            style = typography.headlineMedium.copy(color = colorScheme.background)
        )

        LazyColumn {
            if (childExpenses != 0) {
                item {
                    TitlePriceItem(
                        title = stringResource(R.string.child_expenses),
                        price = childExpenses.toString(),
                        currency = currency
                    )
                }
            }
            items(liabilities) { liability ->
                if (liability.cost != 0) {
                    TitlePriceItem(
                        title = liability.name,
                        price = liability.payment.toString(),
                        currency = currency
                    )
                }
            }
            items(expenses) { expense ->
                TitlePriceItem(
                    modifier = Modifier.clickable { editExpense = expense },
                    title = expense.name,
                    price = expense.expense.toString(),
                    currency = currency
                )
            }
            if (liabilities.size + expenses.size < 4) {
                items(5 - (liabilities.size + expenses.size)) {
                    TitlePriceItem()
                }
            } else {
                item {
                    TitlePriceItem()
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .border(width = 1.dp, color = colorScheme.onBackground)
                        .background(colorScheme.primary)
                        .padding(vertical = 4.dp)
                        .clickable { editExpense = ExpenseUI() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = null,
                        tint = colorScheme.onPrimary
                    )
                }
            }
        }
    }

    editExpense?.let { expense ->
        AddEditExpenseDialog(
            expense = expense,
            currency = currency,
            onDismiss = { editExpense = null },
            onExpenseChange = { editExpense = it },
            onSuccess = {
                onUpsertExpense(expense)
                editExpense = null
            },
            onDelete = {
                onDeleteExpense(expense)
                editExpense = null
            }
        )
    }
}
