package com.karlomaricevic.socialdeal.data.core.helpers

import arrow.core.Either
import arrow.core.left
import arrow.core.right

@Suppress("TooGenericExceptionCaught")
suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Either<Throwable, T> {
    return try {
        val result = apiCall()
        result.right()
    } catch (throwable: Throwable) {
        throwable.left()
    }
}
