package com.cashflow.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cashflow.ui.R
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.StockUI

@Composable
fun IncomeComponent(
    modifier: Modifier = Modifier,
    salary: Int,
    currency: String,
    stocks: SnapshotStateList<StockUI>,
    businesses: SnapshotStateList<BusinessUI>,
    onSalaryChange: (Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.onBackground)
                .padding(horizontal = 8.dp),
            text = stringResource(R.string.income),
            style = typography.headlineMedium.copy(color = colorScheme.background)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(2f),
                text = stringResource(R.string.description),
                style = typography.labelMedium.copy(color = colorScheme.onBackground),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .weight(1f),
                text = stringResource(R.string.cash_flow),
                style = typography.labelMedium.copy(color = colorScheme.onBackground),
                textAlign = TextAlign.Center
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp),
            color = colorScheme.onBackground,
            thickness = 2.dp
        )

        LazyColumn {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.salary),
                            style = typography.labelMedium.copy(color = colorScheme.onBackground),
                            textAlign = TextAlign.Start
                        )
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            color = colorScheme.onBackground,
                            thickness = 1.dp
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    ) {
                        BasicTextField(
                            value = salary.takeIf { it != 0 }?.toString()
                                .orEmpty(),
                            onValueChange = { value ->
                                (value.takeIf { it.isNotBlank() } ?: "0")
                                    .toIntOrNull()
                                    ?.let { salary ->
                                        onSalaryChange(salary)
                                    }
                            },
                            textStyle = typography.labelMedium.copy(
                                color = colorScheme.onBackground,
                                textAlign = TextAlign.Center
                            ),
                            decorationBox = { innerTextField ->
                                Column(
                                    modifier = Modifier
                                ) {
                                    Row {
                                        Text(
                                            text = currency,
                                            style = typography.labelMedium.copy(color = colorScheme.onBackground),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer(Modifier.width(4.dp))
                                        innerTextField()
                                    }
                                    HorizontalDivider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        color = colorScheme.onBackground,
                                        thickness = 1.dp
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true,
                            cursorBrush = SolidColor(colorScheme.onBackground)
                        )
                    }
                }
            }
            item {
                TitlePriceItem(title = stringResource(R.string.interest_dividends))
            }
            items(stocks) { stock ->
                if (stock.income != 0) {
                    TitlePriceItem(
                        title = stock.name,
                        price = stock.income.toString(),
                        currency = currency
                    )
                }
            }
            item {
                TitlePriceItem()
            }
            item {
                TitlePriceItem(title = stringResource(R.string.real_estate_business))
            }
            items(businesses) { business ->
                if (business.income != 0) {
                    TitlePriceItem(
                        title = business.name,
                        price = business.income.toString(),
                        currency = currency
                    )
                }
            }
            if (businesses.size < 4) {
                items(4 - businesses.size) {
                    TitlePriceItem()
                }
            } else {
                item {
                    TitlePriceItem()
                }
            }
        }
    }
}
