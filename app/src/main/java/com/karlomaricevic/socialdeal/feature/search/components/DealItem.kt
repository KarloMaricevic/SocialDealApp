package com.karlomaricevic.socialdeal.feature.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.designSystem.theme.blue100
import com.karlomaricevic.socialdeal.designSystem.theme.gray500
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.feature.core.components.RemoteImage

@Composable
    fun DealItem(
    model: Deal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .background(Color.White)
            .padding(12.dp)
    ) {
        RemoteImage(
            url = model.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = model.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 20.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = model.company,
            style = MaterialTheme.typography.labelMedium,
            color = gray500,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = model.city,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(0.7f),
            color = gray500,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = model.soldLabel,
            style = MaterialTheme.typography.labelMedium,
            color = blue100,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(0.33f),
        )
    }
}

@Preview
@Composable
private fun DealItemPreview() {
    DealItem(
        Deal(
            id = "",
            title = "Cinema ticket + popcorn + drink at Cinema",
            imageUrl = "",
            soldLabel = "Sold: 122",
            company = "Company",
            city = "City",
        )
    )
}

@Preview
@Composable
private fun DealItemLongTextPreview() {
    DealItem(
        Deal(
            id = "",
            title = List(10) { "Cinema ticket + popcorn + drink at Cinema" }
                .joinToString(),
            imageUrl = "",
            soldLabel = List(10) { "Sold: 122" }.joinToString(),
            company = List(10) { "Company" }.joinToString(),
            city = List(10) { "City" }.joinToString(),
        )
    )
}
