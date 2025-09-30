package com.cashflow.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    currencies: SnapshotStateList<String>,
    selectedCurrency: String,
    onUpdateCurrency: (currency: String) -> Unit
) {
    var isCurrenciesExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth().background(colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.clickable { isCurrenciesExpanded = true },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 4.dp),
                        text = selectedCurrency,
                        style = typography.headlineMedium.copy(color = colorScheme.onBackground)
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = colorScheme.onBackground
                    )
                }

                DropdownMenu(
                    expanded = isCurrenciesExpanded,
                    onDismissRequest = { isCurrenciesExpanded = false }
                ) {
                    currencies.forEach { currency ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp),
                                    text = currency,
                                    style = typography.headlineMedium.copy(color = colorScheme.onBackground)
                                )
                            },
                            onClick = {
                                onUpdateCurrency(currency)
                                isCurrenciesExpanded = false
                            }
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
