package br.com.ximendesindustries.bookcase.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.ximendesindustries.bookcase.domain.model.Book

@Composable
fun HomeScreenDefaultUI(
    modifier: Modifier,
    onSearchBook: (query: String) -> Unit,
    books: List<Book> = emptyList()
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearchBook(text)
            },
            label = { Text("Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, color = Color.Gray),
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
        HomeBooksList(books = books)
    }
}