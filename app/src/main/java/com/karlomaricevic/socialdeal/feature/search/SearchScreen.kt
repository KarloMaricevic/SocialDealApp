package com.karlomaricevic.socialdeal.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.feature.core.components.DealItem
import com.karlomaricevic.socialdeal.feature.core.components.DealItemPlaceholder
import com.karlomaricevic.socialdeal.feature.core.components.DefaultErrorScreenIndicator
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenEvent.*
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Content
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Error
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Loading
import com.karlomaricevic.socialdeal.feature.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    innerPadding: PaddingValues,
) {
    val state = viewModel.viewState.collectAsState()
    Box(Modifier.padding(innerPadding)) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xDDDDDDDD)),
        ) {
            when (val currentState = state.value) {
                is Content -> {
                    items(
                        count = currentState.deals.size,
                        key = { index -> currentState.deals[index].id },
                    ) { index ->
                        val deal = currentState.deals[index]
                        DealItem(
                            model = deal,
                            onClick = { viewModel.onEvent(DealsCardClicked(deal.id)) },
                            onFavoritesButtonClick = { viewModel.onEvent(FavoritesButtonClick(deal.id)) },
                        )
                    }
                }

                is Loading -> {
                    items(3) { DealItemPlaceholder() }
                }

                Error -> {
                    item {
                        DefaultErrorScreenIndicator(
                            onRetryClicked = { viewModel.onEvent(RetryButtonClicked) },
                            modifier = Modifier.fillParentMaxSize(),
                        )
                    }
                }
            }
        }
    }
}
