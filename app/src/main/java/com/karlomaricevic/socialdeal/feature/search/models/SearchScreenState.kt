package com.karlomaricevic.socialdeal.feature.search.models

import com.karlomaricevic.domain.core.models.Deal

sealed class SearchScreenState {

    data class Content(val deals: List<Deal>) : SearchScreenState()
    object Error : SearchScreenState()
    object Loading : SearchScreenState()
}
