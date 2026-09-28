package com.example.coilretrofit.presentation.event

sealed interface DialogEvent {
    data class ShowError(val title: String, val message: String):  DialogEvent
    data class ShowCharacterDetails(val characterId: Int): DialogEvent
    data class ConfirmRemoteFavorite(val characterId: Int, val characterName: String): DialogEvent
}