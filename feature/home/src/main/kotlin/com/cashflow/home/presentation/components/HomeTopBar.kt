package com.cashflow.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cashflow.ui.component.language.LanguageIconTitle
import com.cashflow.ui_model.language.AppLanguageUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    currencies: SnapshotStateList<String>,
    selectedCurrency: String,
    appLanguage: AppLanguageUI,
    onUpdateCurrency: (currency: String) -> Unit,
    onSelectLanguage: (AppLanguageUI) -> Unit
) {
    var isCurrenciesExpanded by remember { mutableStateOf(false) }
    var showLanguageBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorScheme.background)
            .windowInsetsPadding(
                WindowInsets.systemBars.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                )
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier
                    .requiredSizeIn(
                        minWidth = 46.dp,
                        minHeight = 26.dp
                    )
                    .clickable(onClick = { showLanguageBottomSheet = true }),
                painter = painterResource(id = appLanguage.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier.width(8.dp))

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

        if (showLanguageBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showLanguageBottomSheet = false },
                shape = shapes.large,
                containerColor = colorScheme.background,
                contentColor = colorScheme.onBackground,
                dragHandle = {
                    Box(
                        modifier = Modifier
                            .padding(13.dp)
                            .requiredSizeIn(
                                minWidth = 70.dp,
                                minHeight = 1.dp
                            )
                            .background(colorScheme.surfaceDim)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            ) {
                AppLanguageUI.entries.forEach { language ->
                    key(language.ordinal) {
                        LanguageIconTitle(
                            modifier = modifier,
                            language = language,
                            onClick = {
                                showLanguageBottomSheet = false
                                onSelectLanguage(language)
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
