package com.karlomaricevic.socialdeal.domain.favorites

import com.karlomaricevic.socialdeal.data.deals.DealsRepository
import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetFavoriteDealsUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dealsRepository: DealsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        val favorites = favoritesRepository.getFavoritesIds()
        dealsRepository.getDeals().map { deals ->
            deals.filter { deal -> favorites.contains(deal.id) }
                .sortedBy { deal -> favorites.indexOf(deal.id) }
                .map { deal -> deal.copy(isFavorite = true) }
        }
    }
}
