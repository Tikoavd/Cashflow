package com.cashflow.ui.utils

fun Int.formatSecondsToTime(format: String = TIME_FORMAT): String {
    val hours = this / SECONDS_IN_HOUR
    val minutes = (this - (hours * SECONDS_IN_HOUR)) / SECONDS_IN_MINUTE
    val seconds = (this - (hours * SECONDS_IN_HOUR) - (minutes * SECONDS_IN_MINUTE))
    return format.format(hours, minutes, seconds)
}
private const val TIME_FORMAT = "%01d:%02d:%02d"
private const val SECONDS_IN_HOUR = 3600
private const val SECONDS_IN_MINUTE = 60
