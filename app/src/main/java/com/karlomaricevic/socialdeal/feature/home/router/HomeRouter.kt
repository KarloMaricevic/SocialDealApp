package com.karlomaricevic.socialdeal.feature.home.router

import com.karlomaricevic.socialdeal.feature.core.navigation.NavigationDestination

object HomeRouter : NavigationDestination {
    private const val HOME_SCREEN_ROUTE = "home"

    override fun route() = HOME_SCREEN_ROUTE
}
