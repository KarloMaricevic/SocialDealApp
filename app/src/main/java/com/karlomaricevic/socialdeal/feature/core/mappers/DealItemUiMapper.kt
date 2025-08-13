package com.karlomaricevic.socialdeal.feature.core.mappers

import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import com.karlomaricevic.socialdeal.feature.core.models.DealItemUi
import javax.inject.Inject

class DealItemUiMapper @Inject constructor() {

    private companion object {
        const val EURO_SYMBOL = "€"
        const val DOLLAR_SYMBOL = "$"
    }

    fun map(deal: Deal, currency: Currency): DealItemUi =
        DealItemUi(
            id = deal.id,
            title = deal.title,
            imageUrl = deal.imageUrl,
            soldLabel = deal.soldLabel,
            company = deal.company,
            city = deal.city,
            isFavorite = deal.isFavorite,
            fromPriceLabel = deal.price[currency]?.fromPrice?.let { fromPrice ->
                (if (currency == Currency.EUR) EURO_SYMBOL else DOLLAR_SYMBOL) + fromPrice
            },
            priceLabel = when (currency) {
                Currency.EUR -> EURO_SYMBOL
                Currency.USD -> DOLLAR_SYMBOL
            } + deal.price[currency]?.price.toString(),
            deal = deal,
        )

    fun mapPriceLabel(deal: Deal, currency: Currency) =
        when (currency) {
            Currency.EUR -> EURO_SYMBOL
            Currency.USD -> DOLLAR_SYMBOL
        } + deal.price[currency]?.price.toString()

    fun mapFromPriceLabel(deal: Deal, currency: Currency) = deal.price[currency]?.fromPrice?.let { fromPrice ->
        (if (currency == Currency.EUR) EURO_SYMBOL else DOLLAR_SYMBOL) + fromPrice
    }
}
