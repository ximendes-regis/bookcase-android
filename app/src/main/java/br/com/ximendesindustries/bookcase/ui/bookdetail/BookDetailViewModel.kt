package br.com.ximendesindustries.bookcase.ui.bookdetail

import androidx.lifecycle.ViewModel
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.ui.bookdetail.model.BookDetailUiState
import br.com.ximendesindustries.bookcase.ui.bookdetail.model.BookDetailViewModelAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class BookDetailViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(BookDetailUiState())
    val uiState: StateFlow<BookDetailUiState> = _uiState.asStateFlow()


    fun performAction(action: BookDetailViewModelAction) {
        when (action) {
            is BookDetailViewModelAction.StartAction -> setBookDetails(action.book)
        }
    }

    private fun setBookDetails(book: Book) {
        _uiState.update { _uiState.value.copy(book = book) }
    }
}