package com.cashflow.home.presentation.mvi

import androidx.compose.runtime.Immutable
import com.cashflow.mvi.MviState

@Immutable
data class HomeState(
    val isLoading: Boolean = true
) : MviState