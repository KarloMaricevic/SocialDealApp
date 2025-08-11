package com.karlomaricevic.socialdeal.domain.search

import com.karlomaricevic.socialdeal.data.deals.DealsRepository
import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetDealsUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dealsRepository: DealsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        val favoritesIds = favoritesRepository.getFavoritesIds()
        dealsRepository.getDeals().map { deals ->
            deals.map { deal -> deal.copy(isFavorite = favoritesIds.contains(deal.id)) }
        }
    }
}
