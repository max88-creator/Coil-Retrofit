package com.example.coilretrofit.domain.use_case.episode

import com.example.coilretrofit.domain.model.Episode
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.EpisodeRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    val repository: EpisodeRepository
) {
    suspend operator fun invoke(page: Int = 1): Result<PaginatedResult<Episode>> {
        return repository.getEpisodes(page)
    }
}