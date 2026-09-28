package com.example.coilretrofit.data.repository

import com.example.coilretrofit.data.mapper.toDomain
import com.example.coilretrofit.data.remote.api.RickAndMortyApi
import com.example.coilretrofit.domain.model.Episode
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) : EpisodeRepository {
    override suspend fun getEpisodes(page: Int): Result<PaginatedResult<Episode>> {
        return runCatching {
            rickAndMortyApi.getEpisodes(page = page)
                .toDomain(page) { episode ->
                    episode.toDomain()
                }
        }
    }
}