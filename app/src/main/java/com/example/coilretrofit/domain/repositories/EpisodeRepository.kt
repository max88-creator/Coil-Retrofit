package com.example.coilretrofit.domain.repositories

import com.example.coilretrofit.domain.model.Episode
import com.example.coilretrofit.domain.model.PaginatedResult

interface EpisodeRepository {
    suspend fun getEpisodes(page: Int): Result<PaginatedResult<Episode>>
}