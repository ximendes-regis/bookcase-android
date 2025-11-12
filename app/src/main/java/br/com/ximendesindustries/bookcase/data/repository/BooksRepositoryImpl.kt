package br.com.ximendesindustries.bookcase.data.repository

import br.com.ximendesindustries.bookcase.data.datasource.RemoteDataSource
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.core.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject() constructor(
    val remoteDataSource: RemoteDataSource
) : BooksRepository {
    override fun getBooks(): Flow<Result<List<Book>>> = flow {
        val books = remoteDataSource.getBooks()
        emit(value = books)
    }
}

interface BooksRepository {
    fun getBooks(): Flow<Result<List<Book>>>
}