package com.karlomaricevic.socialdeal.domain.userConfig

import com.karlomaricevic.socialdeal.data.userConfig.UserConfigRepository
import jakarta.inject.Inject

class GetChangeCurrencyEventsStream @Inject constructor(
    private val userConfigRepository: UserConfigRepository,
) {

    operator fun invoke() = userConfigRepository.changeCurrencyEvents
}
