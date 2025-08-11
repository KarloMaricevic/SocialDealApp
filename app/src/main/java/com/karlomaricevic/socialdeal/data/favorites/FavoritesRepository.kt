package com.karlomaricevic.socialdeal.data.favorites

import com.karlomaricevic.socialdeal.domain.core.models.Deal
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Singleton
class FavoritesRepository @Inject constructor(private val dataSource: FavoritesDataSource) {

    private val _favoriteEvents = MutableSharedFlow<Deal>()
    val favoriteEvents = _favoriteEvents.asSharedFlow()

    suspend fun getFavoritesIds() = dataSource.getFavorites()

    suspend fun addFavorite(deal: Deal) {
        dataSource.addFavorite(deal.id)
        _favoriteEvents.emit(deal.copy(isFavorite = true))
    }

    suspend fun removeFavorite(deal: Deal) {
        dataSource.removeFavorite(deal.id)
        _favoriteEvents.emit(deal.copy(isFavorite = false))
    }

    suspend fun isFavorite(dealId: String) = dataSource.isFavorite(dealId)
}
