package com.karlomaricevic.socialdeal.feature.settings.comonents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karlomaricevic.socialdeal.R
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme
import com.karlomaricevic.socialdeal.designSystem.theme.black
import com.karlomaricevic.socialdeal.designSystem.theme.blue100
import com.karlomaricevic.socialdeal.designSystem.theme.gray500
import com.karlomaricevic.socialdeal.designSystem.theme.white
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import com.karlomaricevic.socialdeal.feature.settings.model.SettingsScreenEvent
import com.karlomaricevic.socialdeal.feature.settings.model.SettingsScreenEvent.OnEurClicked
import com.karlomaricevic.socialdeal.feature.settings.model.SettingsScreenEvent.OnUsdClicked

@Composable
fun CurrencyPicker(
    picked: Currency,
    onEvent: (SettingsScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =modifier.padding(4.dp),
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = stringResource(R.string.favorites_screen_currency_label),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f),
        )
        Box(
            Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .padding(end = 4.dp)
                .clickable { onEvent(OnUsdClicked) }
                .background(if(picked == Currency.USD) blue100 else gray500)
                .border(1.dp, black)
                .padding(8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.favorites_screen_dollar_sign),
                style = MaterialTheme.typography.titleMedium,
                color = white,
            )
        }
        Box(
            Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = 4.dp,
                        bottomEnd = 4.dp
                    )
                )
                .clickable { onEvent(OnEurClicked) }
                .background(if(picked == Currency.EUR) blue100 else gray500)
                .border(1.dp, black)
                .padding(8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.favorites_screen_euro_sign),
                style = MaterialTheme.typography.titleMedium,
                color = white
            )
        }
    }
}

@Preview
@Composable
private fun CurrencyPickerPreview() {
    SocialDealAppTheme {
        CurrencyPicker(
            picked = Currency.EUR,
            onEvent ={},
        )
    }
}
