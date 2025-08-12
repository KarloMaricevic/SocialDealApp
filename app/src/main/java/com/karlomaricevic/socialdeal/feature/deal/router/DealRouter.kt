package com.karlomaricevic.socialdeal.feature.deal.router

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.karlomaricevic.socialdeal.feature.core.navigation.NavigationDestination

object DealRouter: NavigationDestination {

    private const val DEAL_SCREEN_ROOT = "deal"
    const val DEAL_ID_PARAM = "id"

    private const val DEAL_SCREEN_ROUTE =
        "$DEAL_SCREEN_ROOT/{$DEAL_ID_PARAM}"

    override fun route() = DEAL_SCREEN_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(DEAL_ID_PARAM) { type = NavType.StringType })

    fun creteDealRoute(dealId: String) =
        "$DEAL_SCREEN_ROOT/$dealId"
}
