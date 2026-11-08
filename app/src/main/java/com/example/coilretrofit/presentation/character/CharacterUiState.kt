package com.example.coilretrofit.presentation.character

import com.example.coilretrofit.domain.model.Character

data class CharacterUiState(
    val favoriteId: Set<Int> = emptySet(),
    val characters: List<Character> = emptyList(),
    val searchQuery: String = "",
    val currentPage: Int = 1,
    val hasNextPage: Boolean = false,
    val isLoadingMore: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)