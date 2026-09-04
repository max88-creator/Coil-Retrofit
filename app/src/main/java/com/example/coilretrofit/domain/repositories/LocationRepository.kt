package com.example.coilretrofit.domain.repositories

import com.example.coilretrofit.domain.model.Location
import com.example.coilretrofit.domain.model.PaginatedResult

interface LocationRepository {
    suspend fun getLocation(page: Int): Result<PaginatedResult<Location>>
}