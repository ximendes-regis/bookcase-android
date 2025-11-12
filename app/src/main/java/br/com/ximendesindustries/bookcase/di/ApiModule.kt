package br.com.ximendesindustries.bookcase.di

import br.com.ximendesindustries.bookcase.data.api.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }
}
