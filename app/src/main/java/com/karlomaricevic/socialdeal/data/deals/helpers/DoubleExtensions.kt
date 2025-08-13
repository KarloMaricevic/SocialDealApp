package com.karlomaricevic.socialdeal.data.deals.helpers

private const val TWO_DECIMAL_PLACES_MULTIPLIER = 100f

fun Double.roundToTwoDecimalPlaces(): Double {
    return kotlin.math.round(this * TWO_DECIMAL_PLACES_MULTIPLIER) /
        TWO_DECIMAL_PLACES_MULTIPLIER
}
