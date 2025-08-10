package com.karlomaricevic.domain.search

import com.karlomaricevic.data.deals.DealsRepository
import com.karlomaricevic.domain.core.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetDealsUseCase @Inject constructor(
    private val dealsRepository: DealsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        dealsRepository.getDeals()
    }
}
