package com.karlomaricevic.socialdeal.feature.deal.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.karlomaricevic.socialdeal.domain.search.GetDealUseCase
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
): BaseViewModel<DealScreenEvent>() {

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
        }
    }

    private fun loadDealDetails() {
        _viewState.update { Loading }
        launch {
            getDealUseCase(dealId).fold(
                ifLeft = { error -> _viewState.update { Error } },
                ifRight = { deal -> _viewState.update { Content(deal) } }
            )
        }
    }
}