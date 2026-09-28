package com.example.coilretrofit.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coilretrofit.data.local.dao.FavoriteDao
import com.example.coilretrofit.data.local.entity.FavoriteCharacterEntity

@Database(
    entities = [FavoriteCharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RickAndMortyDataBase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}