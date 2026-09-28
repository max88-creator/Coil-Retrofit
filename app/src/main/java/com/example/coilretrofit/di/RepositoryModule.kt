package com.example.coilretrofit.di

import com.example.coilretrofit.data.repository.CharacterRepositoryImpl
import com.example.coilretrofit.data.repository.EpisodeRepositoryImpl
import com.example.coilretrofit.data.repository.FavoriteRepositoryImpl
import com.example.coilretrofit.data.repository.LocationRepositoryImpl
import com.example.coilretrofit.domain.repositories.CharacterRepository
import com.example.coilretrofit.domain.repositories.EpisodeRepository
import com.example.coilretrofit.domain.repositories.FavoriteRepository
import com.example.coilretrofit.domain.repositories.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindEpisodeRepository(episodeRepositoryImpl: EpisodeRepositoryImpl): EpisodeRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    @Singleton
    abstract fun bindLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository
}