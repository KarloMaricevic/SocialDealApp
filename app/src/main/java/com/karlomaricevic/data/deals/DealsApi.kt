package com.karlomaricevic.data.deals

import com.karlomaricevic.data.core.models.DealListResponse
import retrofit2.http.GET

interface DealsApi {

    @GET("demo/deals.json")
    suspend fun getDeals(): DealListResponse
}
