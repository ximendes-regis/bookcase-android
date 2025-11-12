package br.com.ximendesindustries.bookcase.ui.home.model

import br.com.ximendesindustries.bookcase.core.model.RequestScreenUIState
import br.com.ximendesindustries.bookcase.domain.model.Book

data class HomeUiState(
    val books: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList(),
    val screenRequestScreenUIState: RequestScreenUIState = RequestScreenUIState.Loading
)