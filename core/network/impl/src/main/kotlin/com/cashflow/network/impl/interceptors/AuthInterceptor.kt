package com.cashflow.network.impl.interceptors

import com.cashflow.datastore.api.DataStoreRepository
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.annotation.Factory

@Factory
class AuthInterceptor(
    private val dataStoreRepository: DataStoreRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        /*val request = dataStoreRepository.token.takeIf { it.isNotEmpty() }?.let {
            chain.request().newBuilder()
                .header(AUTHORIZATION_KEY, "$BEARER $it")
                .build()
        } ?: chain.request()*/
        return chain.proceed(chain.request())
    }
}
