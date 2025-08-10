package com.karlomaricevic.socialdeal.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karlomaricevic.socialdeal.core.navigation.NavigationEvent.Destination
import com.karlomaricevic.socialdeal.core.navigation.NavigationEvent.NavigateBack
import com.karlomaricevic.socialdeal.core.navigation.NavigationEvent.NavigateUp
import com.karlomaricevic.socialdeal.core.navigation.Navigator
import com.karlomaricevic.socialdeal.feature.home.HomeScreen
import com.karlomaricevic.socialdeal.feature.home.router.HomeRouter

@Suppress("ModifierMissing")
@Composable
fun SocialDealAppNavigation(
    navigator: Navigator,
    navController: NavHostController = rememberNavController(),
) {
    LaunchedEffect(key1 = Unit) {
        navigator.navigationEvent.collect { navigationEvent ->
            when (navigationEvent) {
                NavigateUp -> navController.navigateUp()
                NavigateBack -> navController.popBackStack()
                is Destination -> navController.navigate(
                    route = navigationEvent.destination,
                    builder = navigationEvent.builder,
                )
            }
        }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.surface,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRouter.route(),
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(HomeRouter.route()) { HomeScreen() }
        }
    }
}
