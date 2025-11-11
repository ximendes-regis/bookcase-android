package br.com.ximendesindustries.bookcase.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.ximendesindustries.bookcase.domain.factories.BookFactory
import br.com.ximendesindustries.bookcase.ui.home.uimodel.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun start() {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    books = BookFactory.sampleList(),
                    filteredBooks = BookFactory.sampleList(),
                )
            }
        }
    }

    fun onBookSearch(query: String) {
        when {
            query.isEmpty() -> {
                resetSearch()
            }

            query.length > 3 -> {
                filterBooks(query)
            }
        }
    }

    private fun resetSearch() {
        _uiState.update {
            it.copy(filteredBooks = it.books)
        }
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