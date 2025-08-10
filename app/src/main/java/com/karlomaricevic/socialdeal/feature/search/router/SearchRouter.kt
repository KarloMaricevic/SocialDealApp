package com.karlomaricevic.socialdeal.feature.search.router

import com.karlomaricevic.socialdeal.core.navigation.NavigationDestination

object SearchRouter : NavigationDestination {
    private const val SEARCH_SCREEN_ROUTE = "search"

    override fun route() = SEARCH_SCREEN_ROUTE
}
