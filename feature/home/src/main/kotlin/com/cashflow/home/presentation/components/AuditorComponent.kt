package com.cashflow.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cashflow.ui.R
import com.cashflow.ui_model.cashflow.CashflowUI
import com.cashflow.ui_model.cashflow.TotalUI

@Composable
fun AuditorComponent(
    modifier: Modifier = Modifier,
    total: TotalUI,
    cashflow: CashflowUI,
    onChildCountChange: (childCount: Int) -> Unit,
    onPerChildExpenseChange: (perChildExpense: Int) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .background(colorScheme.onBackground)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.auditor),
            style = typography.headlineMedium.copy(color = colorScheme.background)
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = colorScheme.onBackground,
            thickness = 2.dp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.weight(4f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(R.string.passive_income),
                            style = typography.titleLarge.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "$",
                            style = typography.titleLarge.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(Modifier.width(4.dp))
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = total.passiveIncome.takeIf { it != 0 }?.toString().orEmpty(),
                                style = typography.titleLarge.copy(
                                    color = colorScheme.onBackground,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center
                            )
                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth(),
                                color = colorScheme.onBackground,
                                thickness = 2.dp
                            )
                        }
                    }
                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(R.string.passive_income_desc),
                            style = typography.bodyMedium.copy(color = colorScheme.onBackground)
                        )
                        Spacer(Modifier.weight(1f))
                    }
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.weight(2f),
                    text = stringResource(R.string.total_income),
                    style = typography.titleLarge.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.width(8.dp))
                Row(
                    modifier = Modifier.weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$",
                        style = typography.titleLarge.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = total.totalIncome.takeIf { it != 0 }?.toString().orEmpty(),
                            style = typography.titleLarge.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = colorScheme.onBackground,
                            thickness = 2.dp
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = colorScheme.onBackground,
            thickness = 2.dp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .border(width = 2.dp, color = colorScheme.onBackground)
                        .background(colorScheme.surfaceContainerHighest)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(R.string.num_of_children),
                            style = typography.titleMedium.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(Modifier.width(4.dp))
                        BasicTextField(
                            modifier = Modifier.weight(1f),
                            value = cashflow.childCount.takeIf { it != 0 }?.toString()
                                .orEmpty(),
                            onValueChange = { value ->
                                (value.takeIf { it.isNotBlank() } ?: "0")
                                    .toIntOrNull()
                                    ?.let { childCount ->
                                        onChildCountChange(childCount)
                                    }
                            },
                            textStyle = typography.titleLarge.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            decorationBox = { innerTextField ->
                                Column(
                                    modifier = Modifier
                                ) {
                                    innerTextField()
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

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(R.string.per_child_expense),
                            style = typography.titleMedium.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(Modifier.width(4.dp))
                        BasicTextField(
                            modifier = Modifier.weight(1f),
                            value = cashflow.perChildExpense.takeIf { it != 0 }?.toString()
                                .orEmpty(),
                            onValueChange = { value ->
                                (value.takeIf { it.isNotBlank() } ?: "0")
                                    .toIntOrNull()
                                    ?.let { perChildExpense ->
                                        onPerChildExpenseChange(perChildExpense)
                                    }
                            },
                            textStyle = typography.titleLarge.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            decorationBox = { innerTextField ->
                                Column(
                                    modifier = Modifier
                                ) {
                                    Row {
                                        Text(
                                            text = "$",
                                            style = typography.titleLarge.copy(
                                                color = colorScheme.onBackground,
                                                fontWeight = FontWeight.Bold
                                            ),
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

                Spacer(Modifier.weight(1f))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.weight(2f),
                    text = stringResource(R.string.total_expenses),
                    style = typography.titleLarge.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = "$",
                        style = typography.titleLarge.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.width(4.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = total.totalExpense.takeIf { it != 0 }?.toString().orEmpty(),
                            style = typography.titleLarge.copy(
                                color = colorScheme.onBackground,
                                fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            color = colorScheme.onBackground,
                            thickness = 2.dp
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = colorScheme.onBackground,
            thickness = 2.dp
        )
    }
}
