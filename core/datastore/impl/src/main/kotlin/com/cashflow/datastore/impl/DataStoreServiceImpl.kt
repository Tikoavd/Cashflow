package com.cashflow.datastore.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.cashflow.utils.DEFAULT_BOOLEAN
import com.cashflow.utils.DEFAULT_FLOAT
import com.cashflow.utils.DEFAULT_INT
import com.cashflow.utils.DEFAULT_LONG
import com.cashflow.utils.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
internal class DataStoreServiceImpl(
    private val dataStore: DataStore<Preferences>,
    context: Context
) : DataStoreService {
    private val json: Json = Json


    private val sharedPrefs = context.getSharedPreferences("service", Context.MODE_PRIVATE)
    private val editor = sharedPrefs.edit()

    private fun set(key: String, value: Any?) {
        when (value) {
            is Int -> editor.putInt(key, value).apply()
            is Long -> editor.putLong(key, value).apply()
            is Float -> editor.putFloat(key, value).apply()
            is String -> editor.putString(key, value).apply()
            is Boolean -> editor.putBoolean(key, value).apply()
            else -> throw UnsupportedOperationException("Not implemented type")
        }
    }

    private inline operator fun <reified T> get(
        key: String
    ): T {
        return when (T::class) {
            Int::class -> sharedPrefs.getInt(key, DEFAULT_INT) as T
            Long::class -> sharedPrefs.getLong(key, DEFAULT_LONG) as T
            Float::class -> sharedPrefs.getFloat(key, DEFAULT_FLOAT) as T
            String::class -> sharedPrefs.getString(key, EMPTY_STRING).orEmpty() as T
            Boolean::class -> sharedPrefs.getBoolean(key, DEFAULT_BOOLEAN) as T
            else -> throw UnsupportedOperationException("Not implemented type")
        }
    }

    override fun getAppLanguage(): Flow<String> = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey("app_language")].orEmpty()
    }

    override suspend fun saveAppLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("app_language")] = language
        }
    }

    override fun getCashflow(): Flow<String> = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey("cashflow")].orEmpty()
    }

    override suspend fun saveCashflow(cashflow: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("cashflow")] = cashflow
        }
    }
}
