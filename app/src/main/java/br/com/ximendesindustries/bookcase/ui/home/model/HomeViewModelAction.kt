package br.com.ximendesindustries.bookcase.ui.home.model

sealed class HomeViewModelAction {
    object StartAction : HomeViewModelAction()
    data class SearchBook(val query: String) : HomeViewModelAction()
}
