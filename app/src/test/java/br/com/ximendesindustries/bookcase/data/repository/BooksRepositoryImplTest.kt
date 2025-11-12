package br.com.ximendesindustries.bookcase.data.repository

import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.data.datasource.RemoteDataSource
import br.com.ximendesindustries.bookcase.domain.factories.BookFactory
import br.com.ximendesindustries.bookcase.domain.model.Book
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BooksRepositoryImplTest {

    private val dataSource = mockk<RemoteDataSource>()

    private val repository = BooksRepositoryImpl(remoteDataSource = dataSource)

    @Test
    fun `when repository get books, should call data source with success`() = runTest {
        prepareScenario()

        val result = repository.getBooks().first()

        coVerify(exactly = 1) { dataSource.getBooks() }
        assert(result is Result.Success)
    }

    @Test
    fun `when repository get books, should call data source with error`() = runTest {
        prepareScenario(booksResult = Result.Error("error"))

        val result = repository.getBooks().first()

        coVerify(exactly = 1) { dataSource.getBooks() }
        assert(result is Result.Error)
    }

    private fun prepareScenario(
        booksResult: Result<List<Book>> = Result.Success(BookFactory.sampleList())
    ) {
        coEvery { dataSource.getBooks() } returns booksResult
    }
}