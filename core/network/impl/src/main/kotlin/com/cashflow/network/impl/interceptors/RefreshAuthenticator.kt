package com.cashflow.network.impl.interceptors

import com.cashflow.datastore.api.DataStoreRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.annotation.Factory

@Factory
class RefreshAuthenticator(
    private val dataStoreRepository: DataStoreRepository,
    //private val authApi: AuthApi
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return null/*try {
            dataStoreRepository.token.takeIf { it.isNotEmpty() }?.let { token ->
                dataStoreRepository.refreshToken.takeIf { it.isNotEmpty() }?.let { refreshToken ->
                    val newToken = runBlocking {
                        authApi.refreshToken(
                            AuthDto(
                                token = token,
                                refreshToken = refreshToken
                            )
                        )
                    }
                    dataStoreRepository.token = newToken.auth?.token.orEmpty()
                    dataStoreRepository.refreshToken = newToken.auth?.refreshToken.orEmpty()
                    response.request.newBuilder()
                        .header(AUTHORIZATION_KEY, "$BEARER ${newToken.auth?.token}")
                        .build()
                }
            }
        } catch (_: Exception) {
            dataStoreRepository.token = ""
            dataStoreRepository.refreshToken = ""
            null
        }*/
    }
}