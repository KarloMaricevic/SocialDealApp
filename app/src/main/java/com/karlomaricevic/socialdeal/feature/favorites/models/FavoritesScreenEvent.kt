package com.karlomaricevic.socialdeal.feature.favorites.models

sealed class FavoritesScreenEvent {
    data class DealsCardClicked(val id: String) : FavoritesScreenEvent()
    data class UnfavoritesButtonClick(val id: String) : FavoritesScreenEvent()
    object RetryButtonClicked : FavoritesScreenEvent()
}
