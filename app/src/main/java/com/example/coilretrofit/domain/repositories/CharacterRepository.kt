package com.example.coilretrofit.domain.repositories

import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.model.PaginatedResult

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<PaginatedResult<Character>>
    suspend fun getCharacterById(id: Int): Result<Character>
    suspend fun searchCharacter(name: String, page: Int): Result<PaginatedResult<Character>>
}