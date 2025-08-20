package com.cashflow.mvi

interface Reducer<A : MviAction, S : MviState> {

    fun reduce(action: A, state: S): S
}
