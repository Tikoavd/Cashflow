package com.cashflow.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cashflow.network.api.managers.ErrorManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class MviBaseViewModel<S : MviState, A : MviAction, I : MviIntent, E : MviEffect>(
    initialState: S,
    reducer: Reducer<A, S>
) : ViewModel(), KoinComponent {

    private val errorManager: ErrorManager by inject<ErrorManager>()

    private val _viewState = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    private val actions = MutableSharedFlow<A>()

    private val _effects = Channel<E>(capacity = Channel.CONFLATED)
    val effects = _effects.receiveAsFlow()

    init {
        actions.onEach { action ->
            _viewState.update { reducer.reduce(action, it) }
        }.launchIn(viewModelScope)
    }

    protected abstract fun handleIntent(intent: I)

    fun onIntent(intent: I) {
        handleIntent(intent)
    }

    protected fun onAction(action: A) {
        viewModelScope.launch {
            actions.emit(action)
        }
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }

    /**
     * Catches the exception, and if it can parse the error, sends it to the error manager, otherwise calls the onCatch function
     */
    protected fun <T> Flow<T>.catchErrors(onCatch: (t: Throwable) -> Unit = {}) = catch {
        errorManager.sendError(it)
    }.catch { onCatch(it) }

    protected fun <T> Flow<T>.catchAndDo(
        action: (t: Throwable, isSent: Boolean) -> Unit = {_, _ -> }
    ) = catch {
        errorManager.sendError(it)
        action(it, true)
    }.catch { action(it, false) }

    /**
     * Catches the errors, and if it can parse the error, calls onCatch function, otherwise just catches all errors
     */
    protected fun <T> Flow<T>.parseErrors(onCatch: (t: String) -> Unit) = catch {
        onCatch(errorManager.parseError(it))
    }.catch {}

    protected fun Throwable.parseError(): String = errorManager.parseError(this)
}
