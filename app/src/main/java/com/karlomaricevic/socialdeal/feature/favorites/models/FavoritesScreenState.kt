package com.karlomaricevic.socialdeal.feature.favorites.models

import com.karlomaricevic.socialdeal.feature.core.models.DealItemUi

sealed class FavoritesScreenState {

    data class Content(val deals: List<DealItemUi>) : FavoritesScreenState()
    object Error : FavoritesScreenState()
    object Loading : FavoritesScreenState()
}
