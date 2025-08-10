package com.karlomaricevic.socialdeal.feature.search.viewmodel

import com.karlomaricevic.socialdeal.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel<Unit>() {

    override fun onEvent(event: Unit) {
    }
}
