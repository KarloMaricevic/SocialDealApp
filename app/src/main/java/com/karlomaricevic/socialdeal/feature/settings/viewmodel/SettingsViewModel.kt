package com.karlomaricevic.socialdeal.feature.settings.viewmodel

import com.karlomaricevic.socialdeal.domain.userConfig.ChangeCurrencyPref
import com.karlomaricevic.socialdeal.domain.userConfig.GetCurrencyPref
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency.EUR
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency.USD
import com.karlomaricevic.socialdeal.feature.core.base.BaseViewModel
import com.karlomaricevic.socialdeal.feature.settings.model.SettingsScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCurrencyPref: GetCurrencyPref,
    private val changeCurrencyPref: ChangeCurrencyPref,
): BaseViewModel<SettingsScreenEvent>() {

    private val _currency = MutableStateFlow(EUR)
    val currency = _currency.asStateFlow()

    init {
        launch {
            _currency.update { getCurrencyPref() }
        }
    }

    override fun onEvent(event: SettingsScreenEvent) {
        when(event) {
            SettingsScreenEvent.OnEurClicked -> if(_currency.value != EUR) {
                launch {
                    changeCurrencyPref(EUR)
                    _currency.update { EUR }
                }
            }
            SettingsScreenEvent.OnUsdClicked -> if(_currency.value != USD) {
                launch {
                    changeCurrencyPref(USD)
                    _currency.update { USD }
                }
            }
        }
    }
}
