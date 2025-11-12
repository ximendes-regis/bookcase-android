package br.com.ximendesindustries.bookcase.domain.factories

import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.domain.model.Category

object BookFactory {

    fun create(
        title: String = "Título padrão",
        description: String = "Descrição do livro.",
        author: String = "Autor desconhecido",
        imageUrl: String = "https://via.placeholder.com/150",
        categories: List<Category> = emptyList()
    ): Book {
        return Book(title, description, author, imageUrl, categories)
    }

    fun sampleList(): List<Book> = listOf(
        create(
            title = "O Almanaque de Naval Ravikant",
            description = "Um guia sobre riqueza e felicidade.",
            author = "Eric Jorgenson",
            categories = listOf(Category.BUSINESS)
        ),
        create(
            title = "Sapiens",
            description = "Uma breve história da humanidade.",
            author = "Yuval Noah Harari",
            categories = listOf(Category.BUSINESS)
        )
    )
}