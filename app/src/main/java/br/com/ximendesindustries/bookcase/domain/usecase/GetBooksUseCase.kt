package br.com.ximendesindustries.bookcase.domain.usecase

import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.data.repository.BooksRepository
import br.com.ximendesindustries.bookcase.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetBooksUseCase @Inject() constructor(private val repository: BooksRepository) {
     operator fun invoke(): Flow<Result<List<Book>>> {
        return repository.getBooks()
    }
}


