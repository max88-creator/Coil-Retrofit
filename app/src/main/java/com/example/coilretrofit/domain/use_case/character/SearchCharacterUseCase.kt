package com.example.coilretrofit.domain.use_case.character

import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.CharacterRepository
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(
   val repository: CharacterRepository
) {
    suspend operator fun invoke(name: String, page: Int): Result<PaginatedResult<Character>> {
        return repository.searchCharacter(name, page)
    }
}