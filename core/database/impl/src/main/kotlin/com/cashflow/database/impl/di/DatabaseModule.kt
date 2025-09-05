package com.cashflow.database.impl.di

import android.app.Application
import androidx.room.Room
import com.cashflow.database.impl.AppDatabase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.cashflow.database")
class DatabaseModule {

    @Single
    fun provideDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "app_database.db")
            .fallbackToDestructiveMigration() // TODO remove on release
            .build()

    @Single
    fun provideBusinessDao(database: AppDatabase) = database.businessDao()

    @Single
    fun provideExpenseDao(database: AppDatabase) = database.expenseDao()

    @Single
    fun provideLiabilityDao(database: AppDatabase) = database.liabilityDao()

    @Single
    fun provideStockDao(database: AppDatabase) = database.stockDao()
}
