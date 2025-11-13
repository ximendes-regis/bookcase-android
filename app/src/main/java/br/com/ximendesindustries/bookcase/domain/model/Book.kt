package br.com.ximendesindustries.bookcase.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Book(
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val categories: List<Category>
)