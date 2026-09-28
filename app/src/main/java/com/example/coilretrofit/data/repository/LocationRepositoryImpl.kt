package com.example.coilretrofit.data.repository

import com.example.coilretrofit.data.mapper.toDomain
import com.example.coilretrofit.data.remote.api.RickAndMortyApi
import com.example.coilretrofit.domain.model.Location
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : LocationRepository {
    override suspend fun getLocation(page: Int): Result<PaginatedResult<Location>> {
        return runCatching {
            api.getLocations(page)
                .toDomain(page) { location ->
                    location.toDomain()
                }
        }
    }
}