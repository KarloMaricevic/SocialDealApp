package com.karlomaricevic.socialdeal.feature.search.models

import com.karlomaricevic.socialdeal.feature.core.models.DealItemUi

sealed class SearchScreenState {

    data class Content(val deals: List<DealItemUi>) : SearchScreenState()
    object Error : SearchScreenState()
    object Loading : SearchScreenState()
}
