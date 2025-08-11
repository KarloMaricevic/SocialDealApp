package com.karlomaricevic.socialdeal.domain.core.models

data class Deal(
    val id: String,
    val title: String,
    val imageUrl: String,
    val soldLabel: String,
    val company: String,
    val city: String,
)
