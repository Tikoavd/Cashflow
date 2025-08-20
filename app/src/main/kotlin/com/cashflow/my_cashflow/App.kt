package com.cashflow.my_cashflow

import android.app.Application
import com.cashflow.database.impl.di.DatabaseModule
import com.cashflow.datastore.impl.di.DataStoreModule
import com.cashflow.dispatchers.provider.di.DispatchersModule
import com.cashflow.home.di.HomeModule
import com.cashflow.middleware.internal.di.MiddlewareModule
import com.cashflow.network.impl.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }

    private val modules = listOf(
        AppModule().module,
        DataModule().module,
        DispatchersModule().module,
        DatabaseModule().module,
        DataStoreModule().module,
        MiddlewareModule().module,
        HomeModule().module
    )
}