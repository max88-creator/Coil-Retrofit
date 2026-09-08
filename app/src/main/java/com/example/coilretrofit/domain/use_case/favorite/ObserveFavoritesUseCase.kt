package com.example.coilretrofit.domain.use_case.favorite

import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.model.Episode
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.EpisodeRepository
import com.example.coilretrofit.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(
    val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Character>> {
        return repository.observeFavorites()
    }
}