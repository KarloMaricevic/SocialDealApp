package com.karlomaricevic.socialdeal.feature.deal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme
import com.karlomaricevic.socialdeal.designSystem.theme.blue100
import com.karlomaricevic.socialdeal.designSystem.theme.gray500
import com.karlomaricevic.socialdeal.designSystem.theme.green
import com.karlomaricevic.socialdeal.designSystem.theme.red
import com.karlomaricevic.socialdeal.designSystem.theme.white
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.domain.core.models.Price
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import com.karlomaricevic.socialdeal.feature.core.components.DefaultErrorScreenIndicator
import com.karlomaricevic.socialdeal.feature.core.components.RemoteImage
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent.OnBackButtonClicked
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent.OnRetryButtonClicked
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
    Box(
        Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        when (val currentState = state) {
            is Content -> Content(
                state = currentState,
                onEvent = viewModel::onEvent,
            )

            is Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            is Error -> DefaultErrorScreenIndicator(
                onRetryClick = { viewModel.onEvent(OnRetryButtonClicked) },
                modifier = Modifier.fillMaxSize(),
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp)
                .clip(CircleShape)
                .size(48.dp)
                .clickable { viewModel.onEvent(OnBackButtonClicked) }
                .background(blue100)
                .padding(12.dp),
            tint = white,
        )
    }
}

@Composable
private fun Content(
    state: Content,
    onEvent: (DealScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val deal = state.deal
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 12.dp),
    ) {
        Box(Modifier.padding(bottom = 8.dp)) {
            RemoteImage(
                url = deal.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 0.dp,
                            topStart = 0.dp,
                            bottomEnd = 8.dp,
                            bottomStart = 8.dp,
                        )
                    ),
            )
            Icon(
                painter = painterResource(
                    if (deal.isFavorite)
                        R.drawable.ic_filled_favorite else R.drawable.ic_outlined_favorite
                ),
                contentDescription = stringResource(R.string.default_icon_content_description),
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable {
                        if (state.isFavoriteClickable) {
                            onEvent(DealScreenEvent.OnFavoriteButtonClicked)
                        }
                    }
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.BottomEnd),
                tint = if (deal.isFavorite) red else white,
            )
        }
        Column(Modifier.padding(horizontal = 12.dp)) {
            Text(
                deal.title,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 20.dp),
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = deal.company,
                style = MaterialTheme.typography.labelLarge,
                color = gray500,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.7f),
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = deal.city,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(0.7f),
                color = gray500,
            )
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = state.priceLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = blue100,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.alignByBaseline(),
                )
                Spacer(Modifier.weight(1f))
                if (state.fromPriceLabel != null) {
                    Text(
                        text = state.fromPriceLabel,
                        color = gray500,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.alignByBaseline(),
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = state.priceLabel,
                    color = green,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.alignByBaseline(),
                )
            }
            Spacer(Modifier.padding(vertical = 8.dp))
            Text(
                text = stringResource(R.string.deal_screen_description_section_title),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.padding(vertical = 4.dp))
            if (deal.description != null) {
                Text(
                    text =deal.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            } else {
                Text(
                    text = stringResource(R.string.deal_screen_no_description_indicator),
                    style = MaterialTheme.typography.bodyMedium,
                    color = gray500,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    SocialDealAppTheme {
        Content(
            state = Content(
                deal = Deal(
                    id = "",
                    title = "Title",
                    imageUrl = "",
                    soldLabel = "Sold: 123",
                    company = "Company",
                    city = "City",
                    isFavorite = true,
                    price = hashMapOf(
                        Currency.EUR to Price(123f, 123f)
                    ),
                ),
                priceLabel = "€123",
                fromPriceLabel = "€1000",
            ),
            onEvent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContentLongTextPreview() {
    SocialDealAppTheme {
        Content(
            state = Content(
                Deal(
                    id = "",
                    title = List(20) { "Title" }
                        .joinToString(),
                    imageUrl = "",
                    soldLabel = List(10) { "Sold: 122" }.joinToString(),
                    company = List(10) { "Company" }.joinToString(),
                    city = List(10) { "City" }.joinToString(),
                    price = hashMapOf(
                        Currency.EUR to Price(123f, 123f)
                    ),
                    description = List(20) { "Description" }.joinToString()
                ),
                priceLabel = "€123",
                fromPriceLabel = "€1000",
            ),
            onEvent = {},
        )
    }
}
