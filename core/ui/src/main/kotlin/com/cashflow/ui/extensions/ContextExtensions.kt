package com.cashflow.ui.extensions

import android.content.Context

val Context.versionName: String
    get() = try {
        packageManager.getPackageInfo(packageName, 0).versionName.orEmpty()
    } catch (_: Exception) { "" }