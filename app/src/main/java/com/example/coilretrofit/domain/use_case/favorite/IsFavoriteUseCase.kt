package com.example.coilretrofit.domain.use_case.favorite

import com.example.coilretrofit.domain.repositories.FavoriteRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    val repository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int): Boolean {
        return repository.isFavorite(id)
    }
}