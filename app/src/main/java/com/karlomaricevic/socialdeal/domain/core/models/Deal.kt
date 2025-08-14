package com.karlomaricevic.socialdeal.domain.core.models

import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency

data class Deal(
    val id: String,
    val title: String,
    val imageUrl: String,
    val soldLabel: String,
    val company: String,
    val city: String,
    val isFavorite: Boolean = false,
    val price: HashMap<Currency, Price>,
    val description: String? = null,
)
