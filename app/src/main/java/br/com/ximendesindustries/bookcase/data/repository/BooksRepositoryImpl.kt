package br.com.ximendesindustries.bookcase.data.repository

import br.com.ximendesindustries.bookcase.data.datasource.LocalDataSource
import br.com.ximendesindustries.bookcase.domain.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject() constructor(
    val localDataSource: LocalDataSource
) : BooksRepository {
    override fun getBooks(): Flow<List<Book>> = flow {
        val books = localDataSource.getBooks()
        emit(value = books)
    }
}

interface BooksRepository {
    fun getBooks(): Flow<List<Book>>
}