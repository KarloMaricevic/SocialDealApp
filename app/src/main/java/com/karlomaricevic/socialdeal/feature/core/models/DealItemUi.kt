package com.karlomaricevic.socialdeal.feature.core.models

import com.karlomaricevic.socialdeal.domain.core.models.Deal

data class DealItemUi(
    val id: String,
    val title: String,
    val imageUrl: String,
    val soldLabel: String,
    val company: String,
    val city: String,
    val isFavorite: Boolean = false,
    val fromPriceLabel: String?,
    val priceLabel: String,
    val deal: Deal,
)
