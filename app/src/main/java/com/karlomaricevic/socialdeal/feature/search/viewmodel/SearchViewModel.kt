package com.karlomaricevic.socialdeal.feature.search.viewmodel

import com.karlomaricevic.socialdeal.domain.search.GetDealsUseCase
import com.karlomaricevic.socialdeal.feature.core.base.BaseViewModel
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
    private val getDealsUseCase: GetDealsUseCase
) : BaseViewModel<Unit>() {

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

    override fun onEvent(event: Unit) {
    }
}
