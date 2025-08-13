package com.karlomaricevic.socialdeal.feature.search.viewmodel

import com.karlomaricevic.socialdeal.domain.favorites.FavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.favorites.GetFavoriteEventsStream
import com.karlomaricevic.socialdeal.domain.favorites.UnfavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.search.GetDealsUseCase
import com.karlomaricevic.socialdeal.domain.userConfig.GetCurrencyPref
import com.karlomaricevic.socialdeal.feature.core.base.BaseViewModel
import com.karlomaricevic.socialdeal.feature.core.mappers.DealItemUiMapper
import com.karlomaricevic.socialdeal.feature.core.navigation.NavigationEvent.Destination
import com.karlomaricevic.socialdeal.feature.core.navigation.Navigator
import com.karlomaricevic.socialdeal.feature.deal.router.DealRouter
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenEvent
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenEvent.DealsCardClicked
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenEvent.FavoritesButtonClick
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenEvent.RetryButtonClicked
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Error
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Loading
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenState.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getDealsUseCase: GetDealsUseCase,
    private val favoriteDealUseCase: FavoriteDealUseCase,
    private val unfavoriteDealUseCase: UnfavoriteDealUseCase,
    private val getFavoriteEventsStream: GetFavoriteEventsStream,
    private val getCurrencyPref: GetCurrencyPref,
    private val dealItemMapper: DealItemUiMapper,
    private val navigator: Navigator,
) : BaseViewModel<SearchScreenEvent>() {

    companion object {
        private const val NOT_FOUND_INDICATOR = -1
    }

    private val dealsIdsBeingFavorite = mutableSetOf<String>()

    private val _viewState = MutableStateFlow<SearchScreenState>(Loading)
    val viewState = _viewState.asStateFlow()

    init {
        loadDeals()
        launch {
            getFavoriteEventsStream().collect { changedDeal ->
                _viewState.update { state ->
                    if (state is Content) {
                        val dealIndex = state.deals.indexOfFirst { deal -> deal.id == changedDeal.id }
                        if (dealIndex == NOT_FOUND_INDICATOR) {
                            state
                        }
                        val newDealsItems = state.deals.toMutableList().apply {
                            this[dealIndex] = this[dealIndex].copy(
                                isFavorite = changedDeal.isFavorite,
                                deal = if(this[dealIndex].id == changedDeal.id) changedDeal else this[dealIndex].deal,
                            )
                        }
                        state.copy(deals = newDealsItems)
                    } else state
                }
            }
        }
    }

    override fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is DealsCardClicked -> {
                launch {
                    navigator.emitDestination(
                        Destination(
                            DealRouter.creteDealRoute(event.id)
                        )
                    )
                }
            }

            is FavoritesButtonClick -> {
                if (dealsIdsBeingFavorite.contains(event.id)) {
                    return
                }
                dealsIdsBeingFavorite.add(event.id)
                _viewState.update { state ->
                    if (state is Content) {
                        val dealIndex = state.deals.indexOfFirst { deal -> deal.id == event.id }
                        val deal = state.deals[dealIndex]
                        val updatedDeal = deal.copy(
                            isFavorite = !deal.isFavorite,
                            deal = deal.deal.copy(isFavorite = !deal.isFavorite)
                        )
                        val updatedDeals = state.deals.toMutableList().apply { this[dealIndex] = updatedDeal }
                        Content(
                            deals = updatedDeals,
                            currency = state.currency,
                        )
                    } else {
                        state
                    }
                }
                launch {
                    val content = _viewState.value as? Content ?: return@launch
                    val deal = content.deals.firstOrNull { it.id == event.id }?.deal ?: return@launch
                    if (!deal.isFavorite) {
                        unfavoriteDealUseCase(deal)
                    } else {
                        favoriteDealUseCase(deal)
                    }
                    dealsIdsBeingFavorite.remove(event.id)
                }
            }

            RetryButtonClicked -> loadDeals()
        }
    }

    private fun loadDeals() {
        _viewState.update { Loading }
        launch {
            val currencyPref = getCurrencyPref()
            Timber.d("currencyPref: $currencyPref")
            getDealsUseCase().fold(
                ifLeft = { _ -> _viewState.update { Error } },
                ifRight = { deals ->
                    _viewState.update {
                        Content(
                            deals = deals.map { deal -> dealItemMapper.map(deal = deal, currency = currencyPref) },
                            currency = currencyPref,
                        )
                    }
                }
            )
        }
    }
}
