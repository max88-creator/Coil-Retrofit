package com.example.coilretrofit.domain.use_case.character

import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.getCharacterById(id)
    }
}