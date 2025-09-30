package com.cashflow.my_cashflow

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.navigation.RootNavHost
import com.cashflow.ui.component.snackbar.BoxSnackbarHost
import com.cashflow.ui.extensions.collectWithLifeCycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.getAppLanguage()
        setContent {
            val language by viewModel.language.collectAsStateWithLifecycle()
            val snackBarState = remember { SnackbarHostState() }
            viewModel.errors.collectWithLifeCycle { message ->
                launch {
                    snackBarState.currentSnackbarData?.dismiss()
                    snackBarState.showSnackbar(message)
                }
            }

            SetLanguage(language = language)
            val configuration = LocalConfiguration.current
            val resources = LocalContext.current.resources
            LaunchedEffect(isSystemInDarkTheme()) {
                val locale = Locale(language.iso)
                configuration.setLocale(locale)
                Locale.setDefault(locale)
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            val darkTheme = isSystemInDarkTheme()
            val colors = when {
                dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
                dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
                darkTheme -> darkColorScheme()
                else -> lightColorScheme()
            }
            UpdateSystemBars(isSystemInDarkTheme(), colors.background)
            MaterialTheme(
                colorScheme = colors
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    RootNavHost()

                    BoxSnackbarHost(hostState = snackBarState)
                }
            }
        }
    }

    @Composable
    private fun SetLanguage(language: AppLanguage) {
        val locale = Locale(language.iso)
        val configuration = LocalConfiguration.current
        configuration.setLocale(locale)
        Locale.setDefault(locale)
        val resources = LocalContext.current.resources
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    @Composable
    fun UpdateSystemBars(
        darkMode: Boolean,
        color: Color
    ) {
        val activity = (LocalContext.current as ComponentActivity)
        DisposableEffect(darkMode) {
            val barStyle = when (darkMode) {
                true -> SystemBarStyle.dark(color.toArgb())
                false -> SystemBarStyle.light(
                    color.toArgb(),
                    color.toArgb()
                )
            }
            activity.enableEdgeToEdge(
                statusBarStyle = barStyle,
                navigationBarStyle = barStyle
            )
            onDispose { }
        }
    }
}