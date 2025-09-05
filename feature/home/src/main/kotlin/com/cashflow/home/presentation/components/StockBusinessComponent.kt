package com.cashflow.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun StockBusinessComponent(
    modifier: Modifier = Modifier,
    name: String = "",
    quantity: String = "",
    price: String = ""
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = name,
                style = typography.labelMedium.copy(color = colorScheme.onBackground),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(1f),
                text = quantity,
                style = typography.labelMedium.copy(color = colorScheme.onBackground),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(1f),
                text = price,
                style = typography.labelMedium.copy(color = colorScheme.onBackground),
                textAlign = TextAlign.Center
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            color = colorScheme.onBackground,
            thickness = 1.dp
        )
    }
}
