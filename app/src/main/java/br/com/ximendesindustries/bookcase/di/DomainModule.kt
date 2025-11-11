package br.com.ximendesindustries.bookcase.di

import br.com.ximendesindustries.bookcase.data.repository.BooksRepository
import br.com.ximendesindustries.bookcase.domain.usecase.GetBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetBooksUseCase(
        repository: BooksRepository
    ) = GetBooksUseCase(
        repository = repository
    )
}