package com.karlomaricevic.socialdeal.data.userConfig

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserConfigDatasource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val USER_CONFIG_PREFS_NAME = "user_config_prefs"
        private const val FAVORITES_KEY = "currency_key"
        const val CURRENCY_USD_VALUE = "USD"
        const val CURRENCY_EUR_VALUE = "EUR"
    }

    private val prefs = context.getSharedPreferences(USER_CONFIG_PREFS_NAME, Context.MODE_PRIVATE)

    suspend fun getPreferredCurrency() =
        prefs.getString(FAVORITES_KEY, CURRENCY_EUR_VALUE) ?: CURRENCY_EUR_VALUE

    suspend fun saveEurAsCurrency() {
        prefs.edit { putString(FAVORITES_KEY, CURRENCY_EUR_VALUE) }
    }

    suspend fun saveUsdAsCurrency() {
        prefs.edit { putString(FAVORITES_KEY, CURRENCY_USD_VALUE) }
    }

}
