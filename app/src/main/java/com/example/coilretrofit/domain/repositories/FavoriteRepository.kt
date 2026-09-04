package com.example.coilretrofit.domain.repositories

import com.example.coilretrofit.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun observeFavorites(): Flow<List<Character>>
    suspend fun isFavorite(id: Int): Boolean
    suspend fun addCharacter(character: Character)
    suspend fun deleteCharacter(id: Int)
    suspend fun toggleFavorite(character: Character): Boolean
}