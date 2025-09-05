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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cashflow.ui.R
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.StockUI

@Composable
fun AssetsComponent(
    modifier: Modifier = Modifier,
    savings: Int,
    stocks: SnapshotStateList<StockUI>,
    businesses: SnapshotStateList<BusinessUI>,
    onSavingsChange: (Int) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.onBackground)
                .padding(horizontal = 8.dp),
            text = stringResource(R.string.assets),
            style = typography.headlineMedium.copy(color = colorScheme.background)
        )

        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = stringResource(R.string.savings),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Start
                )
                BasicTextField(
                    modifier = Modifier.weight(1f),
                    value = savings.takeIf { it != 0 }?.toString()
                        .orEmpty(),
                    onValueChange = { value ->
                        (value.takeIf { it.isNotBlank() } ?: "0")
                            .toIntOrNull()
                            ?.let { savings ->
                                onSavingsChange(savings)
                            }
                    },
                    textStyle = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    decorationBox = { innerTextField ->
                        Row {
                            Text(
                                text = "$",
                                style = typography.labelMedium.copy(
                                    color = colorScheme.onBackground,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Start
                            )
                            Spacer(Modifier.width(4.dp))
                            innerTextField()
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    cursorBrush = SolidColor(colorScheme.onBackground)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = colorScheme.onBackground,
                thickness = 2.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.stocks_funds_cds),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.num_of_shares),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.cost_share),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = colorScheme.onBackground,
                thickness = 2.dp
            )
            LazyColumn {
                items(stocks) { stock ->
                    StockBusinessComponent(
                        //TODO add on click edit
                        name = stock.name,
                        quantity = stock.quantity.toString(),
                        price = "$" + stock.price
                    )
                }
                if (stocks.size < 4) {
                    items(5 - stocks.size) {
                        StockBusinessComponent()
                    }
                } else {
                    item {
                        StockBusinessComponent()
                    }
                }
                item {
                    //TODO add on add click
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(width = 1.dp, color = colorScheme.onBackground)
                            .background(colorScheme.primary)
                            .padding(vertical = 4.dp),
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
                .padding(horizontal = 8.dp)
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = colorScheme.onBackground,
                thickness = 2.dp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.real_estate_business),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.down_pay),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.cost),
                    style = typography.labelMedium.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = colorScheme.onBackground,
                thickness = 2.dp
            )
            LazyColumn {
                items(businesses) { business ->
                    StockBusinessComponent(
                        //TODO add on click edit
                        name = business.name,
                        quantity = "$" + business.downPay,
                        price = "$" + business.cost
                    )
                }
                if (businesses.size < 4) {
                    items(5 - businesses.size) {
                        StockBusinessComponent()
                    }
                } else {
                    item {
                        StockBusinessComponent()
                    }
                }
                item {
                    //TODO add on add click
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(width = 1.dp, color = colorScheme.onBackground)
                            .background(colorScheme.primary)
                            .padding(vertical = 4.dp),
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
    }
}
