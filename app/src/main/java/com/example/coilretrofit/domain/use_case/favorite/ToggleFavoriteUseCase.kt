package com.example.coilretrofit.domain.use_case.favorite

import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.repositories.FavoriteRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    val repository: FavoriteRepository
) {
    suspend operator fun invoke(character: Character): Boolean {
        return repository.toggleFavorite(character)
    }
}