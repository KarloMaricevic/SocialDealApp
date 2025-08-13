package com.karlomaricevic.socialdeal.data.core.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PriceResponse(
    @JsonProperty("amount")
    val amount: Float,
)
