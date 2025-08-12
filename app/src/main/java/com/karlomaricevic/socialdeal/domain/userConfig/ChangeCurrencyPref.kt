package com.karlomaricevic.socialdeal.domain.userConfig

import com.karlomaricevic.socialdeal.data.userConfig.UserConfigRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ChangeCurrencyPref @Inject constructor(
    private val repository: UserConfigRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(currency: Currency) = withContext(dispatcher) {
        repository.setPreferredCurrency(currency)
    }
}
