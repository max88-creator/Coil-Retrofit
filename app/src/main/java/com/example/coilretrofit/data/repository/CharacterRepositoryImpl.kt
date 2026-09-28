package com.example.coilretrofit.data.repository

import com.example.coilretrofit.data.mapper.toDomain
import com.example.coilretrofit.data.remote.api.RickAndMortyApi
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.CharacterRepository
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.collections.emptyList

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): Result<PaginatedResult<Character>> {
        return runCatching {
            api.getCharacters(page)
                .toDomain(page) { character ->
                    character.toDomain()
                }
        }
            .recoverCatching { error ->
                if (error is HttpException && error.code() == 404) {
                    PaginatedResult(
                        items = emptyList(),
                        currentPage = page,
                        totalPages = 0,
                        hasNextPage = false
                    )
                } else {
                    throw error
                }
            }
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        return runCatching {
            api.getCharacterById(id)
                .toDomain()
        }
    }

    override suspend fun searchCharacter(
        name: String,
        page: Int
    ): Result<PaginatedResult<Character>> {
        return runCatching {
            api.searchCharacters(
                name = name,
                page = page
            )
                .toDomain(
                    page = page,
                ) { character ->
                    character.toDomain()
                }
        }
            .recoverCatching { error ->
                if (error is HttpException && error.code() == 404) {
                    PaginatedResult(
                        items = emptyList(),
                        currentPage = page,
                        hasNextPage = false,
                        totalPages = 0
                    )
                } else {
                    throw error
                }

            }
    }
}