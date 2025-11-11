package br.com.ximendesindustries.bookcase.domain.model

data class Book(
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val categories: List<Category>
)