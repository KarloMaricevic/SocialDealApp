package com.karlomaricevic.socialdeal.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.karlomaricevic.socialdeal.feature.settings.comonents.CurrencyPicker
import com.karlomaricevic.socialdeal.feature.settings.viewmodel.SettingsViewModel

@Suppress("ModifierMissing")
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    innerPadding: PaddingValues,
) {
    val currency by viewModel.currency.collectAsState()
    Column(Modifier.padding(innerPadding)) {
        CurrencyPicker(currency, viewModel::onEvent)
    }
}
