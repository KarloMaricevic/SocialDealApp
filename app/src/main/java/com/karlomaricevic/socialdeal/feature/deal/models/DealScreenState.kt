package com.karlomaricevic.socialdeal.feature.deal.models

import com.karlomaricevic.socialdeal.domain.core.models.Deal

sealed class DealScreenState {
    data class Content(val deal: Deal) : DealScreenState()
    object Loading : DealScreenState()
    object Error : DealScreenState()
}
