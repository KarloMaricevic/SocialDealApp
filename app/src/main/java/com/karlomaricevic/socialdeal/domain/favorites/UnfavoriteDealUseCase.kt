package com.karlomaricevic.socialdeal.domain.favorites

import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UnfavoriteDealUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(deal: Deal) = withContext(dispatcher) {
        favoritesRepository.removeFavorite(deal)
    }
}
