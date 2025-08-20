package com.cashflow.network.impl.di

import com.cashflow.network.api.services.UserApi
import com.cashflow.network.impl.BuildConfig
import com.cashflow.network.impl.CONTENT_TYPE
import com.cashflow.network.impl.interceptors.AuthInterceptor
import com.cashflow.network.impl.interceptors.RefreshAuthenticator
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit


@Module
@ComponentScan("com.cashflow.network.impl")
class DataModule {

    @Single
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Single
    fun providesOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()

    @Single
    fun providesOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        refreshAuthenticator: RefreshAuthenticator
    ): OkHttpClient.Builder = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .authenticator(refreshAuthenticator)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    httpLoggingInterceptor
                )
            }
        }

    @Single
    fun provideRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://test.ru")
            .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .client(okHttpClient)
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Single
    fun provideJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = false
        prettyPrint = true
        coerceInputValues = true
        encodeDefaults = true
        allowStructuredMapKeys = true
        explicitNulls = true
    }

    @Single
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)
}
