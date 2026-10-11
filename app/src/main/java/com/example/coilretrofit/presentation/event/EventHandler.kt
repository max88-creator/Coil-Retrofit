package com.example.coilretrofit.presentation.event

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.coilretrofit.domain.model.Character
import com.example.coilretrofit.domain.model.CharacterStatus
import com.example.coilretrofit.domain.use_case.character.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.Flow

@Composable
fun ObserveUiEvent(
    uiEvent: Flow<UiEvent>,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(
        uiEvent,
        snackbarHostState
    ) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }
}


fun CharacterStatus.displayName(): String {
    return when (this) {
        CharacterStatus.ALIVE -> "Alive"
        CharacterStatus.DEAD -> "Dead"
        CharacterStatus.UNKNOWN -> "Unknown"
    }
}

@Composable
fun ObserveDialogEvent(
    dialogEvent: Flow<DialogEvent>,
    getCharacterByIdUseCase: GetCharacterByIdUseCase,
    onConfirmRemoveFavorite: (Int) -> Unit = {}
) {
    var activeDialog by remember { mutableStateOf<DialogEvent?>(null) }
    var detailsCharacter by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(
        dialogEvent
    ) {
        dialogEvent.collect { event ->
            activeDialog = event
            if (event is DialogEvent.ShowCharacterDetails) {
                getCharacterByIdUseCase(event.characterId)
                    .onSuccess {
                        detailsCharacter = it
                    }
                    .onFailure {
                        detailsCharacter = null
                        activeDialog = DialogEvent.ShowError("Error", message = it.message.toString())
                    }
            } else {
                detailsCharacter = null
            }
        }
    }

    when(val dialog = activeDialog) {
        is DialogEvent.ShowError -> {
            AlertDialog(
                onDismissRequest = {
                    activeDialog = null
                },
                title = {
                    Text(
                        text = dialog.title
                    )
                },
                text = {
                    Text(
                        text = dialog.message
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            activeDialog = null
                        }
                    ) {
                        Text(
                            text = "Ok"
                        )
                    }
                }
            )
        }
        is DialogEvent.ConfirmRemoteFavorite -> {}
        is DialogEvent.ShowCharacterDetails -> {}
        else -> {}
    }
}