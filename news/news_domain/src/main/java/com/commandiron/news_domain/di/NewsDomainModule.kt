package com.commandiron.news_domain.di

import com.commandiron.news_domain.repository.NewsRepository
import com.commandiron.news_domain.use_cases.GetAllNews
import com.commandiron.news_domain.use_cases.CheckDatabaseIsExist
import com.commandiron.news_domain.use_cases.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NewsDomainModule {

    @ViewModelScoped
    @Provides
    fun provideNewsUseCases(
        repository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            checkDatabaseIsExist = CheckDatabaseIsExist(repository),
            getAllNews = GetAllNews(repository)
        )
    }
}