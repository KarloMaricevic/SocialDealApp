package com.karlomaricevic.socialdeal.domain.favorites

import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import com.karlomaricevic.socialdeal.domain.core.di.IoDispatcher
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavoriteDealUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(deal: Deal) = withContext(dispatcher) {
        favoritesRepository.addFavorite(deal)
    }
}
