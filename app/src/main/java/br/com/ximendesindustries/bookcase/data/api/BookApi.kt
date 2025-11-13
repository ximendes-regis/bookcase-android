package br.com.ximendesindustries.bookcase.data.api

import br.com.ximendesindustries.bookcase.data.model.response.BookResponse
import retrofit2.http.GET

interface BookApi {

    @GET("main/books_data.json")
    suspend fun getBooks(): List<BookResponse>

}