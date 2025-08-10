package com.karlomaricevic.data.core.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DealListResponse(

    @JsonProperty("num_deals")
    val numDeals: Int,

    @JsonProperty("deals")
    val deals: List<DealResponse>
)
