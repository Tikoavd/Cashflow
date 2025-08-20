package com.cashflow.network.impl.managers

import android.content.Context
import com.cashflow.datastore.api.models.AppLanguage
import com.cashflow.network.api.extension.getErrorBody
import com.cashflow.network.api.managers.ErrorManager
import com.cashflow.network.entities.error.ApiErrorDto
import com.cashflow.utils.getEnumValue
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import java.util.Locale

@Single
class ErrorManagerImpl(
    private val json: Json,
    context: Context
) : ErrorManager {
    private val _errors = Channel<String>(capacity = Channel.CONFLATED)
    override val errors = _errors.receiveAsFlow()

    private val errorsRu: Map<String, String> = context.assets.open(ERROR_CODES_RU)
        .bufferedReader()
        .readLines()
        .joinToString(separator = "\n")
        .let { json.decodeFromString(it) }

    override suspend fun sendError(t: Throwable) {
        t.getErrorBody()?.let { errorBody ->
            val error = json.decodeFromString<ApiErrorDto>(errorBody)
            val errorMessage =
                when (Locale.getDefault().language.uppercase().getEnumValue<AppLanguage>()) {
                    AppLanguage.RU -> errorsRu[error.errorCode?.toString()]
                        ?: error.message.orEmpty()

                    AppLanguage.EN -> error.message.orEmpty()
                    null -> error.message.orEmpty()
                }
            _errors.send(errorMessage)
        } ?: throw t
    }

    override fun parseError(t: Throwable): String = t.getErrorBody()?.let { errorBody ->
        val error = json.decodeFromString<ApiErrorDto>(errorBody)
        when (Locale.getDefault().language.uppercase().getEnumValue<AppLanguage>()) {
            AppLanguage.RU -> errorsRu[error.errorCode?.toString()]
                ?: error.message.orEmpty()

            AppLanguage.EN -> error.message.orEmpty()
            null -> error.message.orEmpty()
        }
    }.orEmpty()


    companion object {
        private const val ERROR_CODES_RU = "error_codes_ru.json"
    }
}
