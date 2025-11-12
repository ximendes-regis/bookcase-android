package br.com.ximendesindustries.bookcase.data.datasource

import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.data.api.BookApi
import br.com.ximendesindustries.bookcase.data.factory.BookResponseFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceImplTest {

    private val api = mockk<BookApi>()

    private val dataSource = RemoteDataSourceImpl(api = api)

    @Test
    fun `when data source get books is called, then it should call api with success`() = runTest {
        coEvery { api.getBooks() } returns BookResponseFactory.sampleList()

        val result = dataSource.getBooks()

        coVerify(exactly = 1) { api.getBooks() }
        assert(result is Result.Success)
    }

    @Test
    fun `when data source get books is called, then it should call api with error`() = runTest {
        coEvery { api.getBooks() } throws RuntimeException("Network error")

        val result = dataSource.getBooks()

        coVerify(exactly = 1) { api.getBooks() }
        assert(result is Result.Error)
    }
}