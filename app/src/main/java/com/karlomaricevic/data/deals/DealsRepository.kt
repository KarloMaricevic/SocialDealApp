package com.karlomaricevic.data.deals

import com.karlomaricevic.data.core.helpers.safeApiCall
import com.karlomaricevic.data.core.models.DealListResponse
import com.karlomaricevic.domain.core.models.Deal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DealsRepository @Inject constructor(
    private val dealsApi: DealsApi,
) {

    suspend fun getDeals() = safeApiCall { dealsApi.getDeals() }
        .map { response -> mapDealListResponseToDomain(response) }

    private fun mapDealListResponseToDomain(response: DealListResponse): List<Deal> {
        return response.deals.map { dto ->
            Deal(
                id = dto.unique,
                title = dto.title,
                imageUrl = dto.image,
                company = dto.company,
                city = dto.city,
                soldLabel = dto.soldLabel,
            )
        }
    }
}
