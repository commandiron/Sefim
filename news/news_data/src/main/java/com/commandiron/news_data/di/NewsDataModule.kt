package com.commandiron.news_data.di

import com.commandiron.news_data.repository.NewsRepositoryImpl
import com.commandiron.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDataModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
    ): NewsRepository {
        return NewsRepositoryImpl()
    }
}