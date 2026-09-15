package com.example.coilretrofit.domain.model

data class PaginatedResult<T>(
    val items: List<T>,
    val currentPage: Int,
    val hasNextPage: Boolean,
    val totalPages: Int
)
