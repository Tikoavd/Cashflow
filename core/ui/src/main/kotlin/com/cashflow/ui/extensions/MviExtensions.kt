package com.cashflow.ui.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.cashflow.mvi.MviAction
import com.cashflow.mvi.MviBaseViewModel
import com.cashflow.mvi.MviEffect
import com.cashflow.mvi.MviIntent
import com.cashflow.mvi.MviState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

@SuppressLint("ComposableNaming")
@Composable
fun <S : MviState, A : MviAction, I : MviIntent, E : MviEffect>
        MviBaseViewModel<S, A, I, E>.collectEffects(
    lifecycleState: Lifecycle.State = androidx.lifecycle.Lifecycle.State.STARTED,
    sideEffect: (suspend CoroutineScope.(effect: E) -> Unit)
) {
    val effectsFlow = effects
    val lifecycleOwner = LocalLifecycleOwner.current

    val callback by rememberUpdatedState(newValue = sideEffect)

    LaunchedEffect(effectsFlow, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            effectsFlow.collect { callback(it) }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectWithLifeCycle(
    lifecycleState: Lifecycle.State = androidx.lifecycle.Lifecycle.State.STARTED,
    sideEffect: (suspend CoroutineScope.(value: T) -> Unit)
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val callback by rememberUpdatedState(newValue = sideEffect)

    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            collect { callback(it) }
        }
    }
}
