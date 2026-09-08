package com.example.coilretrofit.domain.use_case.location

import com.example.coilretrofit.domain.model.Location
import com.example.coilretrofit.domain.model.PaginatedResult
import com.example.coilretrofit.domain.repositories.LocationRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    val repository: LocationRepository
) {
    suspend operator fun invoke(page: Int = 1): Result<PaginatedResult<Location>> {
        return repository.getLocation(page)
    }
}