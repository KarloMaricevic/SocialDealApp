package com.karlomaricevic.socialdeal.domain.favorites

import arrow.core.Either.Right
import com.karlomaricevic.socialdeal.data.deals.DealsRepository
import com.karlomaricevic.socialdeal.data.favorites.FavoritesRepository
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import io.kotest.assertions.arrow.core.shouldBeRight
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetFavoriteDealsUseCaseTest {

    private val favoritesRepository: FavoritesRepository = mockk()
    private val dealsRepository: DealsRepository = mockk()
    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var sut: GetFavoriteDealsUseCase

    private val deals = List(10) { index ->
        Deal(
            id = index.toString(),
            title = "Deal$index",
            imageUrl = "",
            soldLabel = "",
            company = "",
            city = "",
            isFavorite = false,
            price = hashMapOf()
        )
    }

    @Before
    fun setUp() {
        sut = GetFavoriteDealsUseCase(favoritesRepository, dealsRepository, dispatcher)
        coEvery { dealsRepository.getDeals() } returns Right(deals)
    }

    @Test
    fun Should_ReturnEmptyList_When_UserHasNoFavorites() = runTest {
        coEvery { favoritesRepository.getFavoritesIds() } returns hashSetOf()

        val result = sut()

        result.shouldBeRight(listOf())
    }

    @Test
    fun Should_ReturnFavoritesInExpectedOrder_When_UserHasFavorites() = runTest {
        val favoriteIds = setOf(deals[2].id, deals[1].id)
        coEvery { favoritesRepository.getFavoritesIds() } returns favoriteIds

        val result = sut()

        result.map { deals -> deals.map { deal -> deal.id } }
            .shouldBeRight(favoriteIds)
    }
}
