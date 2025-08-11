package com.karlomaricevic.socialdeal.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme
import com.karlomaricevic.socialdeal.feature.core.components.Shimmer
import com.karlomaricevic.socialdeal.feature.search.components.DealItem
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Content
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Error
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Loading
import com.karlomaricevic.socialdeal.feature.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val state = viewModel.viewState.collectAsState()

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
                    DealItem(deal)
                }
            }

            is Loading -> { items(3) { PlaceholderItem() } }
            Error -> TODO()
        }
    }
}

@Composable
private fun PlaceholderItem(modifier: Modifier = Modifier) {
    val titleSizeSp = MaterialTheme.typography.titleLarge.fontSize
    val labelSizeSp = MaterialTheme.typography.labelLarge.fontSize
    val titleHeightDp = with(LocalDensity.current) { titleSizeSp.toDp() }
    val labelHeightDp = with(LocalDensity.current) { labelSizeSp.toDp() }
    Column(
        modifier
            .background(Color.White)
            .padding(12.dp)
    ) {
        Shimmer(
            Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(8.dp))
        Shimmer(
            Modifier
                .fillMaxWidth(0.5f)
                .height(titleHeightDp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(8.dp))
        Shimmer(
            Modifier
                .fillMaxWidth(0.3f)
                .height(labelHeightDp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(8.dp))
        Shimmer(
            Modifier
                .fillMaxWidth(0.3f)
                .height(labelHeightDp)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(12.dp))
        Shimmer(
            Modifier
                .fillMaxWidth(0.4f)
                .height(labelHeightDp)
                .clip(RoundedCornerShape(8.dp)),
        )
    }
}

@Preview
@Composable
private fun PlaceholderItemPreview() {
    SocialDealAppTheme { PlaceholderItem() }
}
