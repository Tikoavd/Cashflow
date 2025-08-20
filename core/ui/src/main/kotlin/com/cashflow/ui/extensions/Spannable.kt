package com.cashflow.ui.extensions

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun String.buildSpannable(
    vararg spanText: String,
    spanStyle: SpanStyle
) = buildAnnotatedString {
    this@buildSpannable.split(
        regex = "(${
            spanText.joinToString("|") { Regex.escape(it) }.let { "(?=$it)|(?<=$it)" }
        })".toRegex()
    ).forEach { text ->
        if (spanText.contains(text)) {
            pushStringAnnotation(text, text)
            withStyle(style = spanStyle) {
                append(text)
            }
            pop()
        } else {
            append(text)
        }
    }
}
