package com.example.coilretrofit.presentation.character

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.query
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.use_case.character.GetCharacterByIdUseCase
import com.example.coilretrofit.domain.use_case.character.GetCharactersUseCase
import com.example.coilretrofit.domain.use_case.character.SearchCharacterUseCase
import com.example.coilretrofit.domain.use_case.favorite.ObserveFavoritesUseCase
import com.example.coilretrofit.domain.use_case.favorite.ToggleFavoriteUseCase
import com.example.coilretrofit.presentation.base.BaseViewModel
import com.example.coilretrofit.presentation.event.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
// написать роль UseCase & Events(Dialog & UI) dependencies between them
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val observeFavoritesUseCase: ObserveFavoritesUseCase
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

    init {
        observeFavoritesUseCase().onEach { favorites ->
            _characterUiState.update { state ->
                state.copy(
                    favoriteId = favorites.map { character ->
                        character.id
                    }
                        .toSet()
                )
            }
        }
            .launchIn(viewModelScope)

        searchText.debounce(500)
            .distinctUntilChanged()
            .onEach { query ->
                loadCharacters(
                    query = query,
                    page = 1,
                    append = false
                )
            }
            .launchIn(viewModelScope)

        loadCharacters(
            query = "",
            page = 1,
            append = false
        )
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

    fun onRetry() {
        loadCharacters(
            query = _characterUiState.value.searchQuery,
            page = _characterUiState.value.currentPage,
            append = false
        )
    }

    fun onLoadMore() {
        val state = _characterUiState.value
        if (state.isLoading || state.isLoadingMore || !state.hasNextPage) return
        loadCharacters(
            state.searchQuery,
            page = state.currentPage + 1,
            append = true
        )
    }

    fun onFavoriteClick(
        character: Character
    ) {
        viewModelScope.launch {
            val isFavorite = toggleFavoriteUseCase(character)
            showSnackBar(
                if (isFavorite) {
                    "add to favorite"
                } else {
                    "remove from favorite"
                }
            )
        }
    }

    fun onCharacterClick(
        character: Character
    ) {
        sendDialogEvent(
            DialogEvent.ShowCharacterDetails(character.id)
        )
    }
}