package com.cashflow.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashflow.home.presentation.HomeViewModel
import com.cashflow.home.presentation.components.AssetsComponent
import com.cashflow.home.presentation.components.AuditorComponent
import com.cashflow.home.presentation.components.BalanceSheetHeader
import com.cashflow.home.presentation.components.ExpensesComponent
import com.cashflow.home.presentation.components.HomeTopBar
import com.cashflow.home.presentation.components.IncomeComponent
import com.cashflow.home.presentation.components.LiabilitiesComponent
import com.cashflow.home.presentation.mvi.HomeIntent
import com.cashflow.home.presentation.mvi.HomeState
import com.cashflow.ui.R
import com.cashflow.ui.extensions.collectEffects
import com.cashflow.ui_model.cashflow.BusinessUI
import com.cashflow.ui_model.cashflow.ExpenseUI
import com.cashflow.ui_model.cashflow.LiabilityUI
import com.cashflow.ui_model.cashflow.StockUI
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
        state = state,
        onSalaryChange = { salary -> viewModel.onIntent(HomeIntent.OnSalaryChange(salary)) },
        onChildCountChange = { childCount ->
            viewModel.onIntent(HomeIntent.OnChildCountChange(childCount))
        },
        onPerChildExpenseChange = { perChildExpense ->
            viewModel.onIntent(HomeIntent.OnPerChildExpenseChange(perChildExpense))
        },
        onSavingsChange = { savings -> viewModel.onIntent(HomeIntent.OnSavingsChange(savings)) },
        onUpsertBusiness = { business -> viewModel.onIntent(HomeIntent.OnUpsertBusiness(business)) },
        onUpsertExpense = { expense -> viewModel.onIntent(HomeIntent.OnUpsertExpense(expense)) },
        onUpsertLiability = { liability -> viewModel.onIntent(HomeIntent.OnUpsertLiability(liability)) },
        onUpsertStock = { stock -> viewModel.onIntent(HomeIntent.OnUpsertStock(stock)) },
        onDeleteBusiness = { business -> viewModel.onIntent(HomeIntent.OnDeleteBusiness(business)) },
        onDeleteExpense = { expense -> viewModel.onIntent(HomeIntent.OnDeleteExpense(expense)) },
        onDeleteLiability = { liability -> viewModel.onIntent(HomeIntent.OnDeleteLiability(liability)) },
        onDeleteStock = { stock -> viewModel.onIntent(HomeIntent.OnDeleteStock(stock)) },
        onUpdateCurrency = { currency -> viewModel.onIntent(HomeIntent.OnUpdateCurrency(currency)) }
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onSalaryChange: (salary: Int) -> Unit,
    onChildCountChange: (childCount: Int) -> Unit,
    onPerChildExpenseChange: (perChildExpense: Int) -> Unit,
    onSavingsChange: (savings: Int) -> Unit,
    onUpsertBusiness: (business: BusinessUI) -> Unit,
    onUpsertExpense: (expense: ExpenseUI) -> Unit,
    onUpsertLiability: (liability: LiabilityUI) -> Unit,
    onUpsertStock: (stock: StockUI) -> Unit,
    onDeleteBusiness: (business: BusinessUI) -> Unit,
    onDeleteExpense: (expense: ExpenseUI) -> Unit,
    onDeleteLiability: (liability: LiabilityUI) -> Unit,
    onDeleteStock: (stock: StockUI) -> Unit,
    onUpdateCurrency: (currency: String) -> Unit
) {

    val config = LocalConfiguration.current

    var screenWidth by remember { mutableIntStateOf(0) }
    var screenHeight by remember { mutableIntStateOf(0) }

    val screenWidthDp = with(LocalDensity.current) { screenWidth.toDp() }
    val screenHeightDp = with(LocalDensity.current) { screenHeight.toDp() }

    var scale by remember { mutableFloatStateOf(0.5f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val transformableState =
        rememberTransformableState { zoomChange, offsetChange, rotationChange ->
            scale = (scale * zoomChange).coerceIn(0.5f, 1f)
            val newOffset = offset + offsetChange
            //scale = 1f -> -screenWidth / 2, screenWidth / 2
            //scale = 0.5f -> 0f, 0f
            val mul = (scale - 0.5f) * 2
            offset = Offset(
                x = newOffset.x.coerceIn((-screenWidth / 2) * mul, (screenWidth / 2) * mul),
                y = newOffset.y.coerceIn((-screenHeight / 2) * mul, (screenHeight / 2) * mul)
            )
        }

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopBar(
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.systemBars.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                    )
                ),
                currencies = state.currencies,
                selectedCurrency = state.currency,
                onUpdateCurrency = onUpdateCurrency
            )
        },
        containerColor = colorScheme.background,
        contentColor = colorScheme.onBackground,
        contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged {
                        screenWidth = it.width
                        screenHeight = it.height
                    }
            )
            Column(
                modifier = Modifier
                    .requiredWidth(screenWidthDp * 2)
                    .requiredHeight(screenHeightDp * 2)
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .transformable(state = transformableState)
                    .background(colorScheme.background)
                    .padding(12.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = stringResource(R.string.income_statement),
                    style = typography.headlineLarge.copy(
                        color = colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .border(width = 2.dp, color = colorScheme.onBackground)
                    ) {
                        IncomeComponent(
                            modifier = Modifier.weight(1f),
                            salary = state.cashflow.salary,
                            stocks = state.stocks,
                            businesses = state.businesses,
                            currency = state.currency,
                            onSalaryChange = onSalaryChange
                        )

                        ExpensesComponent(
                            modifier = Modifier.weight(1f),
                            childExpenses = state.total.totalChildExpenses,
                            expenses = state.expenses,
                            liabilities = state.liabilities,
                            currency = state.currency,
                            onUpsertExpense = onUpsertExpense,
                            onDeleteExpense = onDeleteExpense
                        )
                    }

                    AuditorComponent(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(start = 16.dp),
                        total = state.total,
                        cashflow = state.cashflow,
                        currency = state.currency,
                        onChildCountChange = onChildCountChange,
                        onPerChildExpenseChange = onPerChildExpenseChange
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    BalanceSheetHeader(
                        monthlyCashFlow = state.total.totalCashflow,
                        currency = state.currency
                    )

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .border(width = 2.dp, color = colorScheme.onBackground)
                    ) {
                        AssetsComponent(
                            modifier = Modifier.weight(1f),
                            savings = state.cashflow.savings,
                            stocks = state.stocks,
                            businesses = state.businesses,
                            currency = state.currency,
                            onSavingsChange = onSavingsChange,
                            onUpsertStock = onUpsertStock,
                            onDeleteStock = onDeleteStock,
                            onUpsertBusiness = onUpsertBusiness,
                            onDeleteBusiness = onDeleteBusiness
                        )

                        LiabilitiesComponent(
                            modifier = Modifier.weight(1f),
                            totalLoan = state.total.totalLoan.toString(),
                            currency = state.currency,
                            liabilities = state.liabilities,
                            stocks = state.stocks,
                            businesses = state.businesses,
                            onUpsertLiability = onUpsertLiability,
                            onDeleteLiability = onDeleteLiability
                        )
                    }
                }
            }
        }
    }
}

@Preview(apiLevel = 35)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            state = HomeState(),
            onSalaryChange = {},
            onChildCountChange = {},
            onPerChildExpenseChange = {},
            onSavingsChange = {},
            onUpsertBusiness = {},
            onUpsertExpense = {},
            onUpsertLiability = {},
            onUpsertStock = {},
            onDeleteBusiness = {},
            onDeleteExpense = {},
            onDeleteLiability = {},
            onDeleteStock = {},
            onUpdateCurrency = {}
        )
    }
}