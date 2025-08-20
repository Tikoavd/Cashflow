package com.cashflow.ui.component.language

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cashflow.ui.preview.ProjectPreviews
import com.cashflow.ui_model.language.AppLanguageUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.LanguageBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSelectLanguage: (AppLanguageUI) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(0),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.background),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(13.dp)
                    .requiredSizeIn(
                        minWidth = 70.dp,
                        minHeight = 1.dp
                    )
                    .background(MaterialTheme.colorScheme.outline)
                    .align(Alignment.CenterHorizontally)
            )
        }
    ) {
        AppLanguageUI.entries.forEach { language ->
            key(language.ordinal) {
                LanguageIconTitle(
                    modifier = modifier,
                    language = language,
                    onClick = onSelectLanguage
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@ProjectPreviews
@Composable
private fun LanguageBottomSheetPreview() {
    MaterialTheme {
        Column {
            LanguageBottomSheet(
                onDismissRequest = {},
                onSelectLanguage = {}
            )
        }
    }
}
