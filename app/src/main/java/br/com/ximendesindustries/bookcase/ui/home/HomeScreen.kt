package br.com.ximendesindustries.bookcase.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.ximendesindustries.bookcase.core.model.RequestScreenUIState
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.ui.home.components.HomeScreenDefaultUI
import br.com.ximendesindustries.bookcase.ui.home.components.HomeScreenErrorUI
import br.com.ximendesindustries.bookcase.ui.home.components.HomeScreenLoadingUI
import br.com.ximendesindustries.bookcase.ui.home.model.HomeViewModelAction

@Composable
fun HomeScreen(
    modifier: Modifier,
    onBookClick: (book: Book) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.performAction(HomeViewModelAction.StartAction)
    }

    when (uiState.screenRequestScreenUIState) {
        RequestScreenUIState.Loading -> HomeScreenLoadingUI()
        RequestScreenUIState.Success -> HomeScreenDefaultUI(
            modifier = modifier,
            books = uiState.books,
            onSearchBook = {
                viewModel.performAction(HomeViewModelAction.SearchBook(it))
            },
            onBookClick = { book ->
                onBookClick(book)
            }
        )

        RequestScreenUIState.Error -> HomeScreenErrorUI()
    }
}