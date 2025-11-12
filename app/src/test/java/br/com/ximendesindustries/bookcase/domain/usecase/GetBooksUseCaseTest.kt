package br.com.ximendesindustries.bookcase.domain.usecase

import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.data.repository.BooksRepository
import br.com.ximendesindustries.bookcase.domain.factories.BookFactory
import br.com.ximendesindustries.bookcase.domain.model.Book
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetBooksUseCaseTest {

    private val repository = mockk<BooksRepository>()

    private val useCase = GetBooksUseCase(repository)

    @Test
    fun `when use case is invoked, should call repository with success`() = runTest {
        prepareScenario()

        val result = useCase().first()

        assert(result is Result.Success)
    }

    @Test
    fun `when use case is invoked, should call repository with error`() = runTest {
        prepareScenario(booksResult = Result.Error("error"))

        val result = useCase().first()

        assert(result is Result.Error)
    }

    private fun prepareScenario(
        booksResult: Result<List<Book>> = Result.Success(BookFactory.sampleList())
    ) {
        coEvery { repository.getBooks() } returns flowOf(booksResult)
    }
}