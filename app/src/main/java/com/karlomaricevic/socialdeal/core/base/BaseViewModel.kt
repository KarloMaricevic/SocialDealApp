package com.karlomaricevic.socialdeal.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

const val TIMEOUT_DELAY = 5_000L

abstract class BaseViewModel<E> : ViewModel(), CoroutineScope {
    override val coroutineContext = viewModelScope.coroutineContext

    abstract fun onEvent(event: E)
}
