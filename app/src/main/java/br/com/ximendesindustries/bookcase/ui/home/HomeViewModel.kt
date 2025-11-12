package br.com.ximendesindustries.bookcase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ximendesindustries.bookcase.core.model.RequestScreenUIState
import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.domain.usecase.GetBooksUseCase
import br.com.ximendesindustries.bookcase.ui.home.model.HomeUiState
import br.com.ximendesindustries.bookcase.ui.home.model.HomeViewModelAction
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MIN_QUERY_LENGTH = 3

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun performAction(action: HomeViewModelAction) {
        when (action) {
            is HomeViewModelAction.SearchBook -> onBookSearch(action.query)
            HomeViewModelAction.StartAction -> start()
        }
    }

    private fun start() {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            getBooksUseCase().collect { result ->
                when (result) {
                    Result.Loading -> {
                        updateRequestScreenUIState(RequestScreenUIState.Loading)
                    }

                    is Result.Success -> {
                        onGetBooksSuccess(result.data)
                    }

                    is Result.Error -> {
                        updateRequestScreenUIState(RequestScreenUIState.Error)
                    }
                }
            }
        }
    }

    private fun onGetBooksSuccess(books: List<Book>) {
        _uiState.update {
            it.copy(
                books = books,
                filteredBooks = books,
                screenRequestScreenUIState = RequestScreenUIState.Success
            )
        }
    }

    private fun updateRequestScreenUIState(requestState: RequestScreenUIState) {
        _uiState.update {
            it.copy(screenRequestScreenUIState = requestState)
        }
    }

    private fun onBookSearch(query: String) {
        when {
            query.isEmpty() -> {
                resetSearch()
            }

            query.length > MIN_QUERY_LENGTH -> {
                filterBooks(query)
            }
        }
    }

    private fun resetSearch() {
        _uiState.update { it.copy(filteredBooks = it.books) }
    }

    private fun filterBooks(query: String) {
        val filteredBooks = _uiState.value.books.filter { book ->
            book.title.startsWith(query, ignoreCase = true)
        }
        _uiState.update {
            it.copy(filteredBooks = filteredBooks)
        }
    }
}