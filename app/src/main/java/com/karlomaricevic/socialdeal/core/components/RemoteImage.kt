package com.karlomaricevic.socialdeal.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.karlomaricevic.designSystem.theme.blue300
import com.karlomaricevic.designSystem.theme.gray300
import com.karlomaricevic.socialdeal.R

@Composable
fun RemoteImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(R.string.default_image_content_description),
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        loading = { Shimmer(Modifier.matchParentSize()) },
        error = {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(gray300),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = stringResource(R.string.default_icon_content_description),
                    tint = blue300,
                    modifier = Modifier.size(60.dp),
                )
            }
        }
    )
}
