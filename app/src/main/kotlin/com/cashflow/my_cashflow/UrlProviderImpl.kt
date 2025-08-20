package com.cashflow.my_cashflow

import com.cashflow.mycashflow.BuildConfig
import com.cashflow.network.api.providers.UrlProvider
import org.koin.core.annotation.Single

@Single
class UrlProviderImpl(): UrlProvider {
    override fun baseUrl(): String = BuildConfig.BASE_URL
}
