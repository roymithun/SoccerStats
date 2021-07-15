package com.inhouse.soccerstats.ui.di

import com.inhouse.soccerstats.data.repository.DefaultMatchRepository
import com.inhouse.soccerstats.data.repository.MatchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @ViewModelScoped
    @Binds
    abstract fun bindMatchRepository(repository: DefaultMatchRepository): MatchRepository
}