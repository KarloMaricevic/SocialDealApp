package com.karlomaricevic.socialdeal.domain.search

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.karlomaricevic.socialdeal.data.deals.DealsRepository
import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetDealUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dealsRepository: DealsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    private companion object {
        const val DEAL_NOT_FOUND_INDICATOR = "Deal not found";
    }

    suspend operator fun invoke(dealId: String) = withContext(dispatcher) {
        val isFavorite = favoritesRepository.isFavorite(dealId)
        dealsRepository.getDeals().fold(
            ifLeft = { error -> error.left() },
            ifRight = { deals ->
                deals.firstOrNull { it.id == dealId }
                    ?.copy(isFavorite = isFavorite)
                    ?.right()
                    ?: Either.Left(DEAL_NOT_FOUND_INDICATOR)
            }
        )
    }
}