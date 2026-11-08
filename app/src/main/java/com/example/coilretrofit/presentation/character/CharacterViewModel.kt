package com.example.coilretrofit.presentation.character

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.use_case.character.GetCharacterByIdUseCase
import com.example.coilretrofit.domain.use_case.character.GetCharactersUseCase
import com.example.coilretrofit.domain.use_case.character.SearchCharacterUseCase
import com.example.coilretrofit.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase
) : BaseViewModel() {
    private val _characterUiState = MutableStateFlow(
        CharacterUiState()
    )
    val characterUiState = _characterUiState.asStateFlow()
    val searchText = MutableStateFlow("")


    fun onSearchQueryChange(
        query: String
    ) {
        _characterUiState.update { state ->
            state.copy(
                searchQuery = query
            )
        }
        searchText.value = query
    }

    private fun loadCharacters(
        query: String,
        page: Int,
        append: Boolean
    ) {
        viewModelScope.launch {
            _characterUiState.update { state ->
                state.copy(
                    isLoading = !append,
                    isLoadingMore = append
                )
            }


            val result = if (query.isBlank()) {
                getCharactersUseCase(page)
            } else {
                searchCharacterUseCase(
                    name = query,
                    page = page
                )
            }

            result.onSuccess { paginatedResult ->
                _characterUiState.update { state ->
                    state.copy(
                        errorMessage = null,
                        isLoading = false,
                        isLoadingMore = false,
                        currentPage = paginatedResult.currentPage,
                        hasNextPage = paginatedResult.hasNextPage,
                        characters = if (append) {
                            state.characters + paginatedResult.items
                        } else {
                            paginatedResult.items
                        }
                    )
                }
            }
                .onFailure { error ->
                    _characterUiState.update { state ->
                        state.copy(
                            errorMessage = error.message,
                            isLoading = false,
                            isLoadingMore = false,
                        )
                    }
                }
        }
    }
}