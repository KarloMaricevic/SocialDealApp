package com.karlomaricevic.socialdeal.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.karlomaricevic.socialdeal.R
import com.karlomaricevic.socialdeal.designSystem.theme.blue300
import com.karlomaricevic.socialdeal.designSystem.theme.blue700
import com.karlomaricevic.socialdeal.feature.core.navigation.TabItem
import com.karlomaricevic.socialdeal.feature.favorites.FavoritesScreen
import com.karlomaricevic.socialdeal.feature.favorites.viewmodel.FavoritesViewModel
import com.karlomaricevic.socialdeal.feature.search.SearchScreen
import com.karlomaricevic.socialdeal.feature.search.viewmodel.SearchViewModel

@Composable
fun HomeScreen(
    searchViewModel: SearchViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
) {
    val saveableStateHolder = rememberSaveableStateHolder()
    val tabs = listOf(
        TabItem(stringResource(R.string.home_screen_search_tab_name), Icons.Default.Search),
        TabItem(stringResource(R.string.home_screen_favorites_tab_name), Icons.Default.Favorite),
        TabItem(stringResource(R.string.home_screen_settings_tab_name), Icons.Default.Settings),
    )

    var selectedTab by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        icon = { Icon(tab.icon, contentDescription = tab.title) },
                        label = { Text(tab.title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = blue300,
                            unselectedIconColor = blue700,
                            selectedTextColor = blue300,
                            unselectedTextColor = blue700,
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        tabs.indices.forEach { index ->
            if (selectedTab == index) {
                saveableStateHolder.SaveableStateProvider(tabs[index].title) {
                    when (index) {
                        0 -> SearchScreen(viewModel = searchViewModel, innerPadding = innerPadding)
                        1 -> FavoritesScreen(viewModel = favoritesViewModel, innerPadding = innerPadding)
                        2 -> SearchScreen(viewModel = searchViewModel, innerPadding = innerPadding)
                    }
                }
            }
        }
    }
}
