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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cashflow.ui.R

@Composable
fun BalanceSheetHeader(
    modifier: Modifier = Modifier,
    monthlyCashFlow: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            text = stringResource(R.string.balance_sheet_header),
            style = typography.headlineLarge.copy(
                color = colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        )

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = stringResource(R.string.monthly_cash_flow),
                    style = typography.titleLarge.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = stringResource(R.string.monthly_cash_flow_desc),
                    style = typography.bodySmall.copy(color = colorScheme.onBackground)
                )
            }
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
                        text = monthlyCashFlow.takeIf { it != 0 }?.toString().orEmpty(),
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
}
