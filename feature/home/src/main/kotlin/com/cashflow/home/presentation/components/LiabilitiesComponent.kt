package com.cashflow.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cashflow.ui.R
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI

@Composable
fun LiabilitiesComponent(
    modifier: Modifier = Modifier,
    totalLoan: String,
    currency: String,
    liabilities: SnapshotStateList<LiabilityUI>,
    stocks: SnapshotStateList<StockUI>,
    businesses: SnapshotStateList<BusinessUI>,
    onUpsertLiability: (liability: LiabilityUI) -> Unit,
    onDeleteLiability: (liability: LiabilityUI) -> Unit
) {
    var editLiability by remember { mutableStateOf<LiabilityUI?>(null) }

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.onBackground)
                .padding(horizontal = 8.dp),
            text = stringResource(R.string.liabilities),
            style = typography.headlineMedium.copy(color = colorScheme.background)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            LazyColumn {
                items(liabilities) { liability ->
                    TitlePriceItem(
                        modifier = Modifier.clickable { editLiability = liability },
                        title = liability.name,
                        price = liability.cost.toString(),
                        currency = currency
                    )
                }
                if (liabilities.size < 4) {
                    items(5 - liabilities.size) {
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
                            .padding(vertical = 4.dp)
                            .border(width = 1.dp, color = colorScheme.onBackground)
                            .background(colorScheme.primary)
                            .padding(vertical = 4.dp)
                            .clickable { editLiability = LiabilityUI() },
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

        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    color = colorScheme.onBackground,
                    thickness = 2.dp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 8.dp),
                        text = stringResource(R.string.real_estate_business),
                        style = typography.labelMedium.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        text = stringResource(R.string.mortgage_liability),
                        style = typography.labelMedium.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    color = colorScheme.onBackground,
                    thickness = 2.dp
                )

                val emptySize by remember(stocks, businesses) {
                    derivedStateOf {
                        5 - (stocks.count { it.mortgage > 0 } + businesses.count { it.mortgage > 0 })
                    }
                }

                LazyColumn {
                    items(stocks) { stock ->
                        if (stock.mortgage != 0) {
                            TitlePriceItem(
                                title = stock.name,
                                price = stock.mortgage.toString(),
                                currency = currency
                            )
                        }
                    }

                    items(businesses) { business ->
                        if (business.mortgage != 0) {
                            TitlePriceItem(
                                title = business.name,
                                price = business.mortgage.toString(),
                                currency = currency
                            )
                        }
                    }

                    if (emptySize > 1) {
                        items(emptySize) {
                            TitlePriceItem()
                        }
                    } else {
                        item {
                            TitlePriceItem()
                        }
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                color = colorScheme.onBackground,
                thickness = 2.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .weight(2f)
                        .padding(horizontal = 8.dp),
                    text = stringResource(R.string.loan),
                    style = typography.labelMedium.copy(color = colorScheme.onBackground)
                )

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                ) {
                    if (totalLoan.isNotEmpty()) {
                        Text(
                            text = currency,
                            style = typography.labelMedium.copy(color = colorScheme.onBackground),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.width(4.dp))
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = totalLoan,
                        style = typography.labelMedium.copy(color = colorScheme.onBackground),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    editLiability?.let { liability ->
        AddEditLiabilityDialog(
            liability = liability,
            currency = currency,
            onDismiss = { editLiability = null },
            onLiabilityChange = { editLiability = it },
            onSuccess = {
                onUpsertLiability(liability)
                editLiability = null
            },
            onDelete = {
                onDeleteLiability(liability)
                editLiability = null
            }
        )
    }
}
