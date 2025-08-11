package com.karlomaricevic.socialdeal.feature.search.viewmodel

import com.karlomaricevic.socialdeal.domain.favorites.FavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.favorites.UnfavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.search.GetDealsUseCase
import com.karlomaricevic.socialdeal.feature.core.base.BaseViewModel
import com.karlomaricevic.socialdeal.feature.search.models.SearchScreenEvent
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

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getDealsUseCase: GetDealsUseCase,
    private val favoriteDealUseCase: FavoriteDealUseCase,
    private val unfavoriteDealUseCase: UnfavoriteDealUseCase,
) : BaseViewModel<SearchScreenEvent>() {

    private val dealsIdsBeingFavorite = mutableSetOf<String>()

    private val _viewState = MutableStateFlow<SearchScreenState>(Loading)
    val viewState = _viewState.asStateFlow()

    init {
        launch {
            getDealsUseCase().fold(
                ifLeft = { _ -> _viewState.update { Error } },
                ifRight = { deals -> _viewState.update { Content(deals) } },
            )
        }
    }

    override fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.DealsCardClicked -> {}
            is SearchScreenEvent.FavoritesButtonClick -> {
                if(dealsIdsBeingFavorite.contains(event.id)) {
                    return
                }
                dealsIdsBeingFavorite.add(event.id)
                _viewState.update { state ->
                    if (state is Content) {
                        val dealIndex = state.deals.indexOfFirst { deal ->  deal.id == event.id }
                        val deal = state.deals[dealIndex]
                        val updatedDeal = deal.copy(isFavorite = !deal.isFavorite)
                        val updatedDeals = state.deals.toMutableList().apply { this[dealIndex] = updatedDeal }
                        Content(updatedDeals)
                    } else {
                        state
                    }
                }
                launch {
                    val content = _viewState.value as? Content ?: return@launch
                    val deal = content.deals.firstOrNull { it.id == event.id } ?: return@launch
                    if (!deal.isFavorite) {
                        unfavoriteDealUseCase(event.id)
                    } else {
                        favoriteDealUseCase(event.id)
                    }
                    dealsIdsBeingFavorite.remove(event.id)
                }
            }
        }
    }
}
