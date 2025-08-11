package com.karlomaricevic.socialdeal.feature.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme

@Composable
fun DealItemPlaceholder(modifier: Modifier = Modifier) {
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
private fun DealItemPlaceholderPreview() {
    SocialDealAppTheme { DealItemPlaceholder() }
}
