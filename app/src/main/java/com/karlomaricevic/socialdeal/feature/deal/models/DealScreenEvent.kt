package com.karlomaricevic.socialdeal.feature.deal.models

sealed class DealScreenEvent {

    object OnBackButtonClicked: DealScreenEvent()
    object OnRetryButtonClicked: DealScreenEvent()
}
