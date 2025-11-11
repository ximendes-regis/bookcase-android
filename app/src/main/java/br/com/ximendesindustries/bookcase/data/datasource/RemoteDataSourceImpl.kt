package br.com.ximendesindustries.bookcase.data.datasource

import br.com.ximendesindustries.bookcase.core.util.safeApiCall
import br.com.ximendesindustries.bookcase.data.api.BookApi
import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.data.model.response.toBook
import br.com.ximendesindustries.bookcase.domain.model.Book
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: BookApi
) : RemoteDataSource {

    override suspend fun getBooks(): Result<List<Book>> {
        return safeApiCall {
            api.getBooks().map { it.toBook() }
        }
    }
}

interface RemoteDataSource {
    suspend fun getBooks(): Result<List<Book>>
}