package com.karlomaricevic.socialdeal.domain.userConfig

import com.karlomaricevic.socialdeal.data.userConfig.UserConfigRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCurrencyPref @Inject constructor(
    private val repository: UserConfigRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        repository.getPreferredCurrency()
    }
}
