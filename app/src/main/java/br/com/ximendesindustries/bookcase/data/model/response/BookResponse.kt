package br.com.ximendesindustries.bookcase.data.model.response

import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.domain.model.Category
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val categories: List<String>
)

fun BookResponse.toBook() = Book(
    title = title,
    description = description,
    author = author,
    imageUrl = imageUrl,
    categories = mapStringsToCategories(categories)
)

fun mapStringsToCategories(values: List<String>): List<Category> {
    return values.mapNotNull { value ->
        Category.entries.find { it.value.equals(value, ignoreCase = true) }
    }
}
