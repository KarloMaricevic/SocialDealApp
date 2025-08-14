package com.karlomaricevic.socialdeal.feature.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.R
import com.karlomaricevic.socialdeal.designSystem.theme.blue100
import com.karlomaricevic.socialdeal.designSystem.theme.gray500
import com.karlomaricevic.socialdeal.designSystem.theme.green
import com.karlomaricevic.socialdeal.designSystem.theme.red
import com.karlomaricevic.socialdeal.designSystem.theme.white
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.feature.core.models.DealItemUi

@Suppress("ModifierMissing")
@Composable
fun DealItem(
    model: DealItemUi,
    onClick: () -> Unit,
    onFavoritesButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .clickable { onClick() }
            .background(Color.White)
            .padding(12.dp)
    ) {
        Box {
            RemoteImage(
                url = model.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Icon(
                painter = painterResource(
                    if (model.isFavorite)
                        R.drawable.ic_filled_favorite else R.drawable.ic_outlined_favorite
                ),
                contentDescription = stringResource(R.string.default_icon_content_description),
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable { onFavoritesButtonClick() }
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.BottomEnd),
                tint = if (model.isFavorite) red else white,
            )
        }
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = model.soldLabel,
                style = MaterialTheme.typography.labelMedium,
                color = blue100,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .alignByBaseline(),
            )
            Spacer(Modifier.weight(1f))
            if (model.fromPriceLabel != null) {
                Text(
                    text = model.fromPriceLabel,
                    color = gray500,
                    style = MaterialTheme.typography.labelMedium.copy(
                        textDecoration = TextDecoration.LineThrough,
                    ),
                    modifier = Modifier.alignByBaseline(),
                )
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text = model.priceLabel,
                color = green,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.alignByBaseline(),
            )
        }
    }
}

@Preview
@Composable
private fun DealItemPreview() {
    DealItem(
        DealItemUi(
            id = "",
            title = "Cinema ticket + popcorn + drink at Cinema",
            imageUrl = "",
            soldLabel = "Sold: 122",
            company = "Company",
            city = "City",
            priceLabel = "$12.12",
            fromPriceLabel = "€14.12",
            deal = Deal(
                id = "",
                title = "Cinema ticket + popcorn + drink at Cinema",
                imageUrl = "",
                soldLabel = "Sold: 122",
                company = "Company",
                city = "City",
                price = hashMapOf(),
            )
        ),
        onClick = {},
        onFavoritesButtonClick = {},
    )
}

@Preview
@Composable
private fun DealItemLongTextPreview() {
    DealItem(
        DealItemUi(
            id = "",
            title = List(10) { "Cinema ticket + popcorn + drink at Cinema" }
                .joinToString(),
            imageUrl = "",
            soldLabel = List(10) { "Sold: 122" }.joinToString(),
            company = List(10) { "Company" }.joinToString(),
            city = List(10) { "City" }.joinToString(),
            priceLabel = "$12.12",
            fromPriceLabel = "14.12",
            deal = Deal(
                id = "",
                title = "Cinema ticket + popcorn + drink at Cinema",
                imageUrl = "",
                soldLabel = "Sold: 122",
                company = "Company",
                city = "City",
                price = hashMapOf(),
            ),
        ),
        onClick = {},
        onFavoritesButtonClick = {},
    )
}
