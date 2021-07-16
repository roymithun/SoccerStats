package com.inhouse.soccerstats.di

import android.content.Context
import androidx.room.Room
import com.inhouse.soccerstats.data.local.SoccerMatchDao
import com.inhouse.soccerstats.data.local.SoccerMatchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
object TestDataModule {
    @Singleton
    @Provides
    fun provideInMemoryDb(
        @ApplicationContext context: Context,
    ): SoccerMatchDao =
        Room.inMemoryDatabaseBuilder(context, SoccerMatchDatabase::class.java)
            .allowMainThreadQueries()
            .build().soccerMatchDao()
}