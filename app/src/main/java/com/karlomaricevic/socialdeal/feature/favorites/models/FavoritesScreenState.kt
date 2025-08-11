package com.karlomaricevic.socialdeal.feature.favorites.models

import com.karlomaricevic.socialdeal.domain.core.models.Deal

sealed class FavoritesScreenState {

    data class Content(val deals: List<Deal>) : FavoritesScreenState()
    object Error : FavoritesScreenState()
    object Loading : FavoritesScreenState()
}
