package com.inhouse.soccerstats.di

import com.inhouse.soccerstats.data.remote.api.SoccerMatchService
import com.inhouse.soccerstats.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun retrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL).build()

    @Singleton
    @Provides
    fun provideSoccerMatchService(retrofit: Retrofit): SoccerMatchService =
        retrofit.create(SoccerMatchService::class.java)
}