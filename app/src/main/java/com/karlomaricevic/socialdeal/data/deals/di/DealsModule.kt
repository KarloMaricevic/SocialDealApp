package com.karlomaricevic.socialdeal.data.deals.di

import com.karlomaricevic.socialdeal.data.deals.DealsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DealsModule {

    @Provides
    @Singleton
    fun provideDealsApi(retrofit: Retrofit): DealsApi =
        retrofit.create(DealsApi::class.java)
}
