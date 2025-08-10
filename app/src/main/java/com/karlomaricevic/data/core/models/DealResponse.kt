package com.karlomaricevic.data.core.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DealResponse(
    @JsonProperty("unique")
    val unique: String,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("image")
    val image: String,

    @JsonProperty("sold_label")
    val soldLabel: String,

    @JsonProperty("company")
    val company: String,

    @JsonProperty("city")
    val city: String,
)
