package com.cashflow.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cashflow.ui.R
import com.cashflow.ui_model.cashflow.BusinessUI

@Composable
fun AddEditBusinessDialog(
    modifier: Modifier = Modifier,
    business: BusinessUI,
    onDismiss: () -> Unit,
    onBusinessChange: (BusinessUI) -> Unit,
    onSuccess: () -> Unit,
    onDelete: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.onBackground)
                    .padding(horizontal = 8.dp),
                text = stringResource(R.string.real_estate_business_label),
                style = typography.headlineMedium.copy(color = colorScheme.background),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = business.name,
                onValueChange = { name ->
                    onBusinessChange(business.copy(name = name))
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onBackground,
                    unfocusedBorderColor = colorScheme.onBackground,
                    cursorColor = colorScheme.onBackground,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground
                ),
                label = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.name),
                        style = typography.labelMedium.copy(color = colorScheme.outline)
                    )
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = business.downPay.takeIf { it != 0 }?.toString()
                    .orEmpty(),
                onValueChange = { value ->
                    (value.takeIf { it.isNotBlank() } ?: "0")
                        .toIntOrNull()
                        ?.let { downPay ->
                            onBusinessChange(business.copy(downPay = downPay))
                        }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onBackground,
                    unfocusedBorderColor = colorScheme.onBackground,
                    cursorColor = colorScheme.onBackground,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.down_pay_label),
                        style = typography.labelMedium.copy(color = colorScheme.outline)
                    )
                },
                leadingIcon = {
                    Text(
                        text = "$",
                        style = typography.labelMedium.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = business.cost.takeIf { it != 0 }?.toString()
                    .orEmpty(),
                onValueChange = { value ->
                    (value.takeIf { it.isNotBlank() } ?: "0")
                        .toIntOrNull()
                        ?.let { cost ->
                            onBusinessChange(business.copy(cost = cost))
                        }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onBackground,
                    unfocusedBorderColor = colorScheme.onBackground,
                    cursorColor = colorScheme.onBackground,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.cost_label),
                        style = typography.labelMedium.copy(color = colorScheme.outline)
                    )
                },
                leadingIcon = {
                    Text(
                        text = "$",
                        style = typography.labelMedium.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = business.income.takeIf { it != 0 }?.toString()
                    .orEmpty(),
                onValueChange = { value ->
                    (value.takeIf { it.isNotBlank() } ?: "0")
                        .toIntOrNull()
                        ?.let { income ->
                            onBusinessChange(business.copy(income = income))
                        }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onBackground,
                    unfocusedBorderColor = colorScheme.onBackground,
                    cursorColor = colorScheme.onBackground,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.income_label),
                        style = typography.labelMedium.copy(color = colorScheme.outline)
                    )
                },
                leadingIcon = {
                    Text(
                        text = "$",
                        style = typography.labelMedium.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = business.mortgage.takeIf { it != 0 }?.toString()
                    .orEmpty(),
                onValueChange = { value ->
                    (value.takeIf { it.isNotBlank() } ?: "0")
                        .toIntOrNull()
                        ?.let { mortgage ->
                            onBusinessChange(business.copy(mortgage = mortgage))
                        }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorScheme.onBackground,
                    unfocusedBorderColor = colorScheme.onBackground,
                    cursorColor = colorScheme.onBackground,
                    focusedTextColor = colorScheme.onBackground,
                    unfocusedTextColor = colorScheme.onBackground
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.mortgage),
                        style = typography.labelMedium.copy(color = colorScheme.outline)
                    )
                },
                leadingIcon = {
                    Text(
                        text = "$",
                        style = typography.labelMedium.copy(
                            color = colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clickable(onClick = onDismiss)
                        .background(colorScheme.secondary)
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = null,
                        tint = colorScheme.onSecondary
                    )
                }

                if (business.id != 0) {
                    Box(
                        modifier = Modifier
                            .clickable(onClick = onDelete)
                            .background(colorScheme.error)
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = colorScheme.onError
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clickable(onClick = onSuccess)
                        .background(colorScheme.primary)
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
