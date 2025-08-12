package com.karlomaricevic.socialdeal.feature.favorites.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.R
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme
import com.karlomaricevic.socialdeal.designSystem.theme.blue

@Composable
fun NoFavorites(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier
                .size(64.dp)
                .padding(bottom = 8.dp),
            tint = blue,
        )
        Text(
            text = stringResource(R.string.favorites_screen_no_favorites_title),
            color = blue,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoFavoritesPreview() {
    SocialDealAppTheme { NoFavorites() }
}
