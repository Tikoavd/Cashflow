@file:OptIn(InternalSerializationApi::class)
package com.cashflow.screens

import kotlinx.serialization.InternalSerializationApi
import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Serializable
@Stable
sealed class Screens {
    @Serializable
    @Stable
    data object Home : Screens()
}
