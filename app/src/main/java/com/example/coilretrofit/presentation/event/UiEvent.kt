package com.example.coilretrofit.presentation.event

sealed interface UiEvent {
    data class ShowSnackBar(val message: String): UiEvent
}