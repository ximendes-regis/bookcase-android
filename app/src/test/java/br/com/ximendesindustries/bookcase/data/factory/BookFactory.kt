package br.com.ximendesindustries.bookcase.data.factory

import br.com.ximendesindustries.bookcase.data.model.response.BookResponse

object BookResponseFactory {

    fun create(
        title: String = "Título padrão",
        description: String = "Descrição do livro.",
        author: String = "Autor desconhecido",
        imageUrl: String = "https://via.placeholder.com/150",
        categories: List<String> = emptyList()
    ): BookResponse {
        return BookResponse(title, description, author, imageUrl, categories)
    }

    fun sampleList(): List<BookResponse> = listOf(
        create(
            title = "O Almanaque de Naval Ravikant",
            description = "Um guia sobre riqueza e felicidade.",
            author = "Eric Jorgenson",
            categories = listOf("BUSINESS"),
            imageUrl = "url"
        ),
        create(
            title = "Sapiens",
            description = "Uma breve história da humanidade.",
            author = "Yuval Noah Harari",
            categories = listOf("BUSINESS"),
            imageUrl = "url"
        )
    )
}