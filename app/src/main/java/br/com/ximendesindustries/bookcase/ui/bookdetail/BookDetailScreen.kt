package br.com.ximendesindustries.bookcase.ui.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.ui.bookdetail.model.BookDetailViewModelAction

@Composable
fun BookDetailScreen(book: Book) {

    val viewModel: BookDetailViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.performAction(
            action = BookDetailViewModelAction.StartAction(book = book)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uiState.value.book?.title.orEmpty(),
            fontSize = 18.sp
        )
    }
}