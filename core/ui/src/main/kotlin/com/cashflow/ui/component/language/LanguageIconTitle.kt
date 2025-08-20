package com.cashflow.ui.component.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cashflow.ui.preview.ProjectPreviews
import com.cashflow.ui_model.language.AppLanguageUI

@Composable
fun LanguageIconTitle(
    modifier: Modifier = Modifier,
    language: AppLanguageUI,
    onClick: (AppLanguageUI) -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = { onClick(language) })
            .padding(
                vertical = 8.dp,
                horizontal = 24.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = language.icon),
            contentDescription = null,
            modifier = Modifier
                .requiredSizeIn(
                    minWidth = 46.dp,
                    minHeight = 26.dp
                ),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = stringResource(id = language.languageName),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onBackground)
        )
    }
}

@ProjectPreviews
@Composable
private fun LanguageIconTitlePreview() {
    MaterialTheme {
        LanguageIconTitle(
            language = AppLanguageUI.RU,
            modifier = Modifier.wrapContentHeight(),
            onClick = {}
        )
    }
}
