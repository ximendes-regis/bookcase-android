package br.com.ximendesindustries.bookcase.ui.bookdetail.model

import br.com.ximendesindustries.bookcase.domain.model.Book

sealed class BookDetailViewModelAction {
    data class StartAction(val book: Book): BookDetailViewModelAction()
}