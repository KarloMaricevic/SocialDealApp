package com.karlomaricevic.socialdeal.feature.favorites.viewmodel

import com.karlomaricevic.socialdeal.domain.favorites.FavoriteDealUseCase
import com.karlomaricevic.socialdeal.domain.favorites.GetFavoriteDealsUseCase
import com.karlomaricevic.socialdeal.domain.favorites.UnfavoriteDealUseCase
import com.karlomaricevic.socialdeal.feature.core.base.BaseViewModel
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent.DealsCardClicked
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent.RetryButtonClicked
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenEvent.UnfavoritesButtonClick
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState.Content
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState.Error
import com.karlomaricevic.socialdeal.feature.favorites.models.FavoritesScreenState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteDealsUseCase: GetFavoriteDealsUseCase,
    private val favoriteDealUseCase: FavoriteDealUseCase,
    private val unfavoriteDealUseCase: UnfavoriteDealUseCase,
) : BaseViewModel<FavoritesScreenEvent>() {

    private val _viewState = MutableStateFlow<FavoritesScreenState>(Loading)
    val viewState = _viewState.asStateFlow()

    init {
        loadFavorites()
    }

    override fun onEvent(event: FavoritesScreenEvent) {
        when (event) {
            is DealsCardClicked -> {}
            is UnfavoritesButtonClick -> {
                _viewState.update { state ->
                    if (state is Content) {
                        val dealIndex = state.deals.indexOfFirst { deal -> deal.id == event.id }
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
                }
            }

            RetryButtonClicked -> loadFavorites()
        }
    }

    private fun loadFavorites() {
        _viewState.update { Loading }
        launch {
            getFavoriteDealsUseCase().fold(
                ifLeft = { _ -> _viewState.update { Error } },
                ifRight = { deals -> _viewState.update { Content(deals) } },
            )
        }
    }
}
