package com.karlomaricevic.socialdeal.data.deals

import com.karlomaricevic.socialdeal.data.core.helpers.safeApiCall
import com.karlomaricevic.socialdeal.data.core.models.DealListResponse
import com.karlomaricevic.socialdeal.data.deals.helpers.roundToTwoDecimalPlaces
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.domain.core.models.Price
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DealsRepository @Inject constructor(
    private val dealsApi: DealsApi,
) {

    companion object {
        private const val BASE_IMAGE_URL = "https://images.socialdeal.nl"
        private const val EUR_TO_USD_RATE = 1.09
    }

    suspend fun getDeals() = safeApiCall { dealsApi.getDeals() }
        .map { response -> mapDealListResponseToDomain(response) }

    private fun mapDealListResponseToDomain(response: DealListResponse): List<Deal> {
        return response.deals.map { dto ->
            Deal(
                id = dto.unique,
                title = dto.title,
                imageUrl = BASE_IMAGE_URL + dto.image,
                company = dto.company,
                city = dto.city,
                soldLabel = dto.soldLabel,
                price = hashMapOf(
                    Currency.EUR to Price(
                        price = dto.prices.price.amount,
                        fromPrice = dto.prices.fromPrice?.amount,
                    ),
                    Currency.USD to Price(
                        price = (dto.prices.price.amount * EUR_TO_USD_RATE)
                            .roundToTwoDecimalPlaces()
                            .toFloat(),
                        fromPrice = dto.prices.price.amount * EUR_TO_USD_RATE
                            .roundToTwoDecimalPlaces()
                            .toFloat(),
                    ),
                ),
            )
        }
    }
}
