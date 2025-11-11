package br.com.ximendesindustries.bookcase.di

import br.com.ximendesindustries.bookcase.data.datasource.LocalDataSource
import br.com.ximendesindustries.bookcase.data.datasource.LocalDataSourceImpl
import br.com.ximendesindustries.bookcase.data.datasource.RemoteDataSource
import br.com.ximendesindustries.bookcase.data.datasource.RemoteDataSourceImpl
import br.com.ximendesindustries.bookcase.data.repository.BooksRepository
import br.com.ximendesindustries.bookcase.data.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideBookRepository(
        repository: BooksRepositoryImpl
    ): BooksRepository

    @Binds
    abstract fun provideLocalDataSource(
        dataSource: LocalDataSourceImpl
    ): LocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(
        dataSource: RemoteDataSourceImpl
    ): RemoteDataSource
}
