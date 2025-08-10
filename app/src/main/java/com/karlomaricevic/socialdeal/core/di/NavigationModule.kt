package com.karlomaricevic.socialdeal.core.di

import com.karlomaricevic.socialdeal.core.navigation.Navigator
import com.karlomaricevic.socialdeal.core.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}
