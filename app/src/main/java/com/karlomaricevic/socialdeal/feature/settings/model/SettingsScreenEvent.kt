package com.karlomaricevic.socialdeal.feature.settings.model

sealed class SettingsScreenEvent {
    object OnEurClicked : SettingsScreenEvent()
    object OnUsdClicked : SettingsScreenEvent()
}
