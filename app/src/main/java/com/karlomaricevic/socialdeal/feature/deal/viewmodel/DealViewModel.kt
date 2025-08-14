package com.karlomaricevic.socialdeal.feature.deal.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.karlomaricevic.socialdeal.domain.core.models.Deal
import com.karlomaricevic.socialdeal.domain.favorites.FavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.favorites.UnfavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.search.GetDealUseCase
import com.karlomaricevic.socialdeal.domain.userConfig.GetCurrencyPref
import com.karlomaricevic.socialdeal.domain.userConfig.models.Currency
import com.karlomaricevic.socialdeal.feature.core.base.BaseViewModel
import com.karlomaricevic.socialdeal.feature.core.navigation.NavigationEvent
import com.karlomaricevic.socialdeal.feature.core.navigation.Navigator
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenEvent
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Content
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Error
import com.karlomaricevic.socialdeal.feature.deal.models.DealScreenState.Loading
import com.karlomaricevic.socialdeal.feature.deal.router.DealRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DealViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDealUseCase: GetDealUseCase,
    private val navigator: Navigator,
    private val getCurrencyPref: GetCurrencyPref,
    private val favoriteDealUseCase: FavoriteDealUseCase,
    private val removeFavoriteDealUseCase: UnfavoriteDealUseCase,
) : BaseViewModel<DealScreenEvent>() {

    private companion object {
        const val EURO_SYMBOL = "€"
        const val DOLLAR_SYMBOL = "$"
    }

    val dealId: String = checkNotNull(savedStateHandle[DealRouter.DEAL_ID_PARAM])

    private val _viewState = MutableStateFlow<DealScreenState>(Loading)
    val viewState = _viewState.asStateFlow()

    init {
        loadDealDetails()
    }

    override fun onEvent(event: DealScreenEvent) {
        when (event) {
            DealScreenEvent.OnBackButtonClicked -> launch {
                navigator.emitDestination(NavigationEvent.NavigateBack)
            }

            DealScreenEvent.OnRetryButtonClicked -> loadDealDetails()
            DealScreenEvent.OnFavoriteButtonClicked -> {
                _viewState.update { state -> if (state is Content) state.copy(isFavoriteClickable = false) else state }
                launch {
                    if (_viewState.value is Content) {
                        val deal = (_viewState.value as Content).deal
                        if (deal.isFavorite) {
                            removeFavoriteDealUseCase(deal)
                        } else {
                            favoriteDealUseCase(deal)
                        }
                        _viewState.update { state ->
                            if (state is Content) state.copy(
                                isFavoriteClickable = true,
                                deal = deal.copy(isFavorite = !state.deal.isFavorite)
                            ) else state
                        }
                    }
                }
            }
        }
    }

    private fun loadDealDetails() {
        _viewState.update { Loading }
        launch {
            val currencyPref = getCurrencyPref()
            getDealUseCase(dealId).fold(
                ifLeft = { error -> _viewState.update { Error } },
                ifRight = { deal ->
                    _viewState.update {
                        Content(
                            deal = deal,
                            priceLabel = mapPriceLabel(deal, currencyPref),
                            fromPriceLabel = mapFromPriceLabel(deal, currencyPref),
                        )
                    }
                })
        }
    }

    fun mapPriceLabel(deal: Deal, currency: Currency) =
        when (currency) {
            Currency.EUR -> EURO_SYMBOL
            Currency.USD -> DOLLAR_SYMBOL
        } + deal.price[currency]?.price.toString()

    fun mapFromPriceLabel(deal: Deal, currency: Currency) = deal.price[currency]?.fromPrice?.let { fromPrice ->
        (if (currency == Currency.EUR) EURO_SYMBOL else DOLLAR_SYMBOL) + fromPrice
    }
}
