package com.cashflow.home.presentation.mvi

import org.koin.core.annotation.Single
import com.cashflow.mvi.Reducer

@Single
class HomeReducer : Reducer<HomeAction, HomeState> {

    override fun reduce(action: HomeAction, state: HomeState): HomeState =
        when (action) {
            else -> state
        }
}