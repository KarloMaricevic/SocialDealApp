package com.karlomaricevic.socialdeal.feature.search.models

sealed class SearchScreenEvent {

    data class DealsCardClicked(val id: String) : SearchScreenEvent()
    data class FavoritesButtonClick(val id: String) : SearchScreenEvent()
    object RetryButtonClicked : SearchScreenEvent()
}
