package com.karlomaricevic.socialdeal.data.core.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PricesResponse(
    @JsonProperty("price")
    val price: PriceResponse,

    @JsonProperty("from_price")
    val fromPrice: PriceResponse?,
)
