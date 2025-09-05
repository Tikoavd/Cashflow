package com.cashflow.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitlePriceItem(
    modifier: Modifier = Modifier,
    title: String = "",
    price: String = "",
    currency: String = ""
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = title,
                style = typography.labelMedium.copy(color = colorScheme.onBackground),
                textAlign = TextAlign.Center
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
            Row {
                if (currency.isNotEmpty() && price.isNotEmpty()) {
                    Text(
                        text = currency,
                        style = typography.labelMedium.copy(color = colorScheme.onBackground),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.width(4.dp))
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = price,
                    style = typography.labelMedium.copy(color = colorScheme.onBackground),
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                color = colorScheme.onBackground,
                thickness = 1.dp
            )
        }
    }
}
