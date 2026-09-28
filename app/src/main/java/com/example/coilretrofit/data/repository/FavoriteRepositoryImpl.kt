package com.example.coilretrofit.data.repository

import com.example.coilretrofit.data.local.dao.FavoriteDao
import com.example.coilretrofit.data.mapper.toDomain
import com.example.coilretrofit.data.mapper.toFavoriteEntity
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) :
    FavoriteRepository {
    override fun observeFavorites(): Flow<List<Character>> {
        return dao.observeFavorites().map { favorites ->
            favorites.map { favorite ->
                favorite.toDomain()
            }
        }
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return dao.isFavorite(id)
    }

    override suspend fun addCharacter(character: Character) {
        dao.insertFavorite(item = character.toFavoriteEntity())
    }

    override suspend fun deleteCharacter(id: Int) {
        dao.deleteFavorite(id)
    }

    override suspend fun toggleFavorite(character: Character): Boolean {
        val isFavoriteState = dao.isFavorite(character.id)
        if (isFavoriteState) {
            dao.deleteFavorite(character.id)
            return false
        } else {
            dao.insertFavorite(character.toFavoriteEntity())
            return true
        }
    }
}