package com.cashflow.home.presentation

import com.cashflow.mvi.MviBaseViewModel
import com.cashflow.home.presentation.mvi.HomeAction
import com.cashflow.home.presentation.mvi.HomeEffect
import com.cashflow.home.presentation.mvi.HomeIntent
import com.cashflow.home.presentation.mvi.HomeReducer
import com.cashflow.home.presentation.mvi.HomeState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    reducer: HomeReducer
) : MviBaseViewModel<HomeState, HomeAction, HomeIntent, HomeEffect>(HomeState(), reducer) {

    override fun handleIntent(intent: HomeIntent) {
        TODO("Not yet implemented")
    }
}