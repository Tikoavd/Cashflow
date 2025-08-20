package com.cashflow.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

@SuppressLint("HardwareIds")
fun Context.getSerial() = runCatching {
    Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}.getOrNull().orEmpty()
