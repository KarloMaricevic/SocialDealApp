package com.karlomaricevic.socialdeal.data.favorites

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit
import javax.inject.Singleton

@Singleton
class FavoritesDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    private companion object {
        const val FAVORITES_PREFS_NAME = "favorites_prefs"
        const val FAVORITES_KEY = "favorites_key"
    }

    private val prefs = context.getSharedPreferences(FAVORITES_PREFS_NAME, Context.MODE_PRIVATE)

    suspend fun getFavorites(): Set<String> = prefs.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()

    suspend fun addFavorite(dealId: String) {
        val newFavorites = getFavorites().toMutableSet().apply { add(dealId) }
        prefs.edit { putStringSet(FAVORITES_KEY, newFavorites) }
    }

    suspend fun removeFavorite(dealId: String) {
        val newFavorites = getFavorites().toMutableSet().apply { remove(dealId) }
        prefs.edit { putStringSet(FAVORITES_KEY, newFavorites) }
    }

    suspend fun isFavorite(dealId: String): Boolean = getFavorites().contains(dealId)
}
