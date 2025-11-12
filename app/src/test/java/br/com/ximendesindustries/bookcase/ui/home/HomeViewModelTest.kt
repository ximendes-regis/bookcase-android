package br.com.ximendesindustries.bookcase.ui.home

import br.com.ximendesindustries.bookcase.MainDispatcherRule
import br.com.ximendesindustries.bookcase.core.model.RequestScreenUIState
import br.com.ximendesindustries.bookcase.core.util.Result
import br.com.ximendesindustries.bookcase.domain.factories.BookFactory
import br.com.ximendesindustries.bookcase.domain.model.Book
import br.com.ximendesindustries.bookcase.domain.usecase.GetBooksUseCase
import br.com.ximendesindustries.bookcase.ui.home.model.HomeViewModelAction
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.collections.List

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getBooksUseCase = mockk<GetBooksUseCase>()

    private val homeViewModel = HomeViewModel(
        getBooksUseCase = getBooksUseCase
    )

    @Test
    fun `when view model start, should load books with success`() = runTest {
        prepareScenario()

        homeViewModel.performAction(HomeViewModelAction.StartAction)

        coVerify(exactly = 1) { getBooksUseCase() }
        assert(homeViewModel.uiState.value.books.isNotEmpty())

    }

    @Test
    fun `when view model start, should load books with error`() = runTest {
        prepareScenario(booksResult = Result.Error("error"))
        homeViewModel.performAction(HomeViewModelAction.StartAction)

        coVerify(exactly = 1) { getBooksUseCase() }
        assert(homeViewModel.uiState.value.books.isEmpty())

        assert(homeViewModel.uiState.value.screenRequestScreenUIState is RequestScreenUIState.Error)

    }

    @Test
    fun `when view model start, should start loading state`() = runTest {
        prepareScenario()
        assert(homeViewModel.uiState.value.screenRequestScreenUIState is RequestScreenUIState.Loading)
    }

    @Test
    fun `when query has less than 4 characters, should not filter books`() = runTest {
        prepareScenario(
            booksResult = Result.Success(
                data = listOf(
                    BookFactory.create(title = "Moby Dick"),
                    BookFactory.create(title = "Harry Potter"),
                    BookFactory.create(title = "The Lord of the Rings")
                )
            )
        )
        homeViewModel.performAction(HomeViewModelAction.StartAction)
        advanceUntilIdle()

        homeViewModel.performAction(action = HomeViewModelAction.SearchBook("Mob"))

        assert(homeViewModel.uiState.value.filteredBooks.size == 3)
    }

    @Test
    fun `when query has 4 or more characters, should filter books`() = runTest {
        prepareScenario(
            booksResult = Result.Success(
                data = listOf(
                    BookFactory.create(title = "Moby Dick"),
                    BookFactory.create(title = "Harry Potter"),
                    BookFactory.create(title = "The Lord of the Rings")
                )
            )
        )
        homeViewModel.performAction(HomeViewModelAction.StartAction)
        advanceUntilIdle()

        homeViewModel.performAction(action = HomeViewModelAction.SearchBook("harry"))

        assert(homeViewModel.uiState.value.filteredBooks.size == 1)
    }

    @Test
    fun `when query is empty should show all books`() = runTest {
        prepareScenario(
            booksResult = Result.Success(
                data = listOf(
                    BookFactory.create(title = "Moby Dick"),
                    BookFactory.create(title = "Harry Potter"),
                    BookFactory.create(title = "The Lord of the Rings")
                )
            )
        )
        homeViewModel.performAction(HomeViewModelAction.StartAction)
        advanceUntilIdle()

        homeViewModel.performAction(action = HomeViewModelAction.SearchBook(""))

        assert(homeViewModel.uiState.value.filteredBooks.size == 3)
    }

    private fun prepareScenario(
        booksResult: Result<List<Book>> = Result.Success(BookFactory.sampleList())
    ) {
        coEvery { getBooksUseCase() } returns flowOf(value = booksResult)
    }
}