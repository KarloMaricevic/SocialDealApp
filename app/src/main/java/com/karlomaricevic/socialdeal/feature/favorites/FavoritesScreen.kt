package com.karlomaricevic.socialdeal.feature.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.designSystem.theme.black
import com.karlomaricevic.socialdeal.feature.core.components.DealItem
import com.karlomaricevic.socialdeal.feature.core.components.DealItemPlaceholder
import com.karlomaricevic.socialdeal.feature.core.components.DefaultErrorScreenIndicator
import com.karlomaricevic.socialdeal.feature.favorites.components.NoFavorites
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent.DealsCardClicked
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent.RetryButtonClicked
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent.UnfavoritesButtonClick
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState.Content
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState.Error
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState.Loading
import com.karlomaricevic.socialdeal.feature.favorites.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {
    val state = viewModel.viewState.collectAsState()
    when (val currentState = state.value) {
        is Content -> {
            if (currentState.deals.isEmpty()) {
                NoFavorites()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize().background(Color(0xDDDDDDDD)),
                ) {
                    items(
                        count = currentState.deals.size,
                        key = { index -> currentState.deals[index].id },
                    ) { index ->
                        val deal = currentState.deals[index]
                        DealItem(
                            model = deal,
                            onClick = { viewModel.onEvent(DealsCardClicked(deal.id)) },
                            onFavoritesButtonClick = { viewModel.onEvent(UnfavoritesButtonClick(deal.id)) },
                        )
                    }
                }
            }
        }

        is Loading -> {
            LazyColumn(
                modifier = Modifier
                    .background(black)
                    .fillMaxSize()
            ) {
                items(3) { DealItemPlaceholder() }
            }
        }

        is Error -> {
            DefaultErrorScreenIndicator(
                onRetryClicked = { viewModel.onEvent(RetryButtonClicked) },
                modifier = Modifier
                    .fillMaxSize()
                    .background(black)
            )
        }
    }
}
