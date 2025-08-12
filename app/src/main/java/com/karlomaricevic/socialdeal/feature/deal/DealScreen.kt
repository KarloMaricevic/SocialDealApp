package com.karlomaricevic.socialdeal.feature.deal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.karlomaricevic.socialdeal.feature.core.components.DefaultErrorScreenIndicator
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent.OnRetryButtonClicked
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent.OnBackButtonClicked
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Content
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Error
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Loading
import com.karlomaricevic.socialdeal.feature.deal.viewmodel.DealViewModel

@Suppress("ModifierMissing")
@Composable
fun DetailsScreen(
    backStackEntry: NavBackStackEntry,
    innerPadding: PaddingValues,
    viewModel: DealViewModel = hiltViewModel(backStackEntry),
) {
    val state by viewModel.viewState.collectAsState()
    Column(Modifier.padding(innerPadding)) {
        when (val currentState = state) {
            is Content -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Need to show deal: ${currentState.deal.title}",
                        modifier = Modifier.padding(horizontal = 12.dp),
                    )
                }
            }

            is Loading -> {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is Error -> DefaultErrorScreenIndicator(
                onRetryClick = { viewModel.onEvent(OnRetryButtonClicked) },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
