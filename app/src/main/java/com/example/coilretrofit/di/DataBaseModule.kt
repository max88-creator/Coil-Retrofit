package com.example.coilretrofit.di

import android.content.Context
import androidx.room.Room
import com.example.coilretrofit.data.local.dao.FavoriteDao
import com.example.coilretrofit.data.local.database.RickAndMortyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): RickAndMortyDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = RickAndMortyDataBase::class.java,
            name = "main_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(rickAndMortyDataBase: RickAndMortyDataBase): FavoriteDao {
        return rickAndMortyDataBase.favoriteDao()
    }
}