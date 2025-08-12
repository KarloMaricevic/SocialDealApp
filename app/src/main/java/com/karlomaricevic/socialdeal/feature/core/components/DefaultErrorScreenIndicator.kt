package com.karlomaricevic.socialdeal.feature.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.R
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme
import com.karlomaricevic.socialdeal.designSystem.theme.blue

@Composable
fun DefaultErrorScreenIndicator(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_outline_warning),
            contentDescription = stringResource(R.string.default_icon_content_description),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(200.dp),
            tint = blue,
        )
        Text(
            text = stringResource(R.string.default_error_title),
            color = blue,
            modifier = Modifier.padding(bottom = 20.dp),
        )
        Button(onClick = { onRetryClick() }) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview
@Composable
private fun ErrorIndicatorPreview() {
    SocialDealAppTheme {
        DefaultErrorScreenIndicator(
            onRetryClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
