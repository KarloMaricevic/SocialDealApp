package com.karlomaricevic.socialdeal.data.userConfig

import com.karlomaricevic.socialdeal.data.userConfig.UserConfigDatasource.Companion.CURRENCY_EUR_VALUE
import com.karlomaricevic.socialdeal.data.userConfig.UserConfigDatasource.Companion.CURRENCY_USD_VALUE
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class UserConfigRepository @Inject constructor(private val dataSource: UserConfigDatasource) {

    private val _changeCurrencyEvents = MutableSharedFlow<Currency>()
    val changeCurrencyEvents = _changeCurrencyEvents.asSharedFlow()

    suspend fun getPreferredCurrency() = when(dataSource.getPreferredCurrency()) {
            CURRENCY_EUR_VALUE -> Currency.EUR
            CURRENCY_USD_VALUE -> Currency.USD
            else -> Currency.EUR
    }

    suspend fun setPreferredCurrency(currency: Currency) {
        when(currency) {
            Currency.EUR -> dataSource.saveEurAsCurrency()
            Currency.USD -> dataSource.saveUsdAsCurrency()
        }
        _changeCurrencyEvents.emit(currency)
    }
}
