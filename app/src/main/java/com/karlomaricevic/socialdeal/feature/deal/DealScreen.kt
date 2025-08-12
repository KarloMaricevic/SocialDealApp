package com.karlomaricevic.socialdeal.feature.deal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.karlomaricevic.socialdeal.R
import com.karlomaricevic.socialdeal.designSystem.theme.blue100
import com.karlomaricevic.socialdeal.designSystem.theme.gray500
import com.karlomaricevic.socialdeal.designSystem.theme.red
import com.karlomaricevic.socialdeal.designSystem.theme.white
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.feature.core.components.DefaultErrorScreenIndicator
import com.karlomaricevic.socialdeal.feature.core.components.RemoteImage
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent.*
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Content
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Error
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Loading
import com.karlomaricevic.socialdeal.feature.deal.viewmodel.DealViewModel

@Composable
fun DetailsScreen(
    backStackEntry: NavBackStackEntry,
    viewModel: DealViewModel = hiltViewModel(backStackEntry),
    innerPadding: PaddingValues,
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
                onRetryClicked = { viewModel.onEvent(OnRetryButtonClicked) },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

