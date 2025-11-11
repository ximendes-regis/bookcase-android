package br.com.ximendesindustries.bookcase.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.domain.factories.BookFactory
import br.com.ximendesindustries.bookcase.ui.theme.Spacing

@Composable
fun HomeBooksList(books: List<Book>) {
    LazyColumn(
        modifier = Modifier.padding(Spacing.spacing16dp)
    ) {
        items(books) { book ->
            HomeBooksListItem(book = book)
        }
    }
}

@Composable
fun HomeBooksListItem(book: Book) {
    Text(text = book.title)
}

@Composable
@Preview(showBackground = true)
fun HomeBooksListPreview() {
    HomeBooksList(books = BookFactory.sampleList())
}