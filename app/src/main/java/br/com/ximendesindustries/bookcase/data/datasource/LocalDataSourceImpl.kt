package br.com.ximendesindustries.bookcase.data.datasource

import br.com.ximendesindustries.bookcase.domain.factories.BookFactory
import br.com.ximendesindustries.bookcase.domain.model.Book
import javax.inject.Inject

class LocalDataSourceImpl @Inject() constructor() : LocalDataSource {
    override fun getBooks(): List<Book> {
        return BookFactory.sampleList()
    }
}

interface LocalDataSource {
    fun getBooks(): List<Book>
}