package com.karlomaricevic.data.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.karlomaricevic.data.BASE_API_URL
import com.karlomaricevic.socialdeal.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

private const val TIMEOUT_IN_SECONDS = 30L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply { level = Level.BODY })
            }
            connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        }.build()

    @Provides
    @Singleton
    fun provideParser(): ObjectMapper =
        ObjectMapper().apply { registerKotlinModule() }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        parser: ObjectMapper,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(httpClient)
            .addConverterFactory(JacksonConverterFactory.create(parser))
            .build()
}
