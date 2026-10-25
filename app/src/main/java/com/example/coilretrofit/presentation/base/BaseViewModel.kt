package com.example.coilretrofit.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coilretrofit.presentation.event.DialogEvent
import com.example.coilretrofit.presentation.event.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _dialogEvent = Channel<DialogEvent>(Channel.BUFFERED)
    val dialogEvent = _dialogEvent.receiveAsFlow()

    protected fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

    protected fun sendDialogEvent(dialogEvent: DialogEvent) {
        viewModelScope.launch {
            _dialogEvent.send(dialogEvent)
        }
    }

    protected fun showError(
        title: String, message: String
    ) {
        sendDialogEvent(
            DialogEvent.ShowError(title, message)
        )
    }

    protected fun showSnackBar(
        message: String
    ) {
        sendUiEvent(
            UiEvent.ShowSnackBar(message)
        )
    }
}