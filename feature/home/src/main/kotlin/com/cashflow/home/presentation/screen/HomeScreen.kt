package com.cashflow.home.presentation.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashflow.home.presentation.HomeViewModel
import com.cashflow.home.presentation.mvi.HomeState
import com.cashflow.ui.extensions.collectEffects
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    viewModel.collectEffects { effect ->
        when (effect) {
            else -> Unit
        }
    }

    HomeScreen(
        state = state
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState
) {
    Text(
        text = "Home Screen",
        style = MaterialTheme.typography.displayLarge,
    )
}

@Preview(apiLevel = 35)
@Composable
private fun HomeScreenPreview() {

}