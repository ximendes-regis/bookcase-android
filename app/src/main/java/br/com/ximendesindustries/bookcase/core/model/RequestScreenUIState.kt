package br.com.ximendesindustries.bookcase.core.model

sealed class RequestScreenUIState {
    object Loading : RequestScreenUIState()
    object Error : RequestScreenUIState()
    object Success : RequestScreenUIState()
}