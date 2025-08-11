package com.karlomaricevic.socialdeal.data.favorites

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepository @Inject constructor(private val dataSource: FavoritesDataSource) {

    suspend fun getFavoritesIds() = dataSource.getFavorites()

    suspend fun addFavorite(dealId: String) = dataSource.addFavorite(dealId)

    suspend fun removeFavorite(dealId: String) = dataSource.removeFavorite(dealId)

    suspend fun isFavorite(dealId: String) = dataSource.isFavorite(dealId)
}
