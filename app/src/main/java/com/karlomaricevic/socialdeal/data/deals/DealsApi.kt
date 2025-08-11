package com.karlomaricevic.socialdeal.data.deals

import com.karlomaricevic.socialdeal.data.core.models.DealListResponse
import retrofit2.http.GET

interface DealsApi {

    @GET("demo/deals.json")
    suspend fun getDeals(): DealListResponse
}
