package br.com.ximendesindustries.bookcase.ui.home.uimodel

import br.com.ximendesindustries.bookcase.domain.model.Book

data class HomeUiState(
    val books: List<Book> = emptyList(),
    val filteredBooks: List<Book> = emptyList()
)