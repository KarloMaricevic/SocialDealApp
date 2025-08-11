package com.karlomaricevic.socialdeal.domain.favorites

import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import javax.inject.Inject

class GetFavoriteEventsStream @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {

    operator fun invoke() = favoritesRepository.favoriteEvents
}
