package com.cashflow.utils

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import java.util.Locale

fun Double.formatThousand(separator: String = " "): String =
    DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.US))
        .format(this)
        .replace(",", separator)
        .replace(".", ",")

fun String.formatThousand(separator: String = " "): String {
    val floatingPart = if ('.' in this || ',' in this) this.takeLastWhile { it != '.' && it != ',' } else ""
    val formatted = this.replace(",", ".").toDoubleOrNull()?.formatThousand(separator)?.replaceAfter(",", floatingPart)?.let {
        if (floatingPart.isNotEmpty() && '.' in this) it.replace(',', '.')
        else if (floatingPart.isEmpty()) it.replace(",", "")
        else it
    }
    return formatted ?: this
}
