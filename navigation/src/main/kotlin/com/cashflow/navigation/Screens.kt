package com.cashflow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cashflow.home.presentation.screen.HomeRoute
import com.cashflow.screens.Screens

fun NavGraphBuilder.homeScreen(
) {
    composable<Screens.Home> {
        HomeRoute()
    }
}
