package com.commandiron.news_data.di

import android.content.Context
import androidx.room.Room
import com.commandiron.news_data.local.NewsCallback
import com.commandiron.news_data.local.NewsDao
import com.commandiron.news_data.local.NewsDatabase
import com.commandiron.news_data.repository.NewsRepositoryImpl
import com.commandiron.news_domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDataModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
        provider: Provider<NewsDao>
    ): NewsDatabase{
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).addCallback(NewsCallback(provider)).build()
    }

    @Provides
    fun provideDao(database: NewsDatabase) = database.dao

    @Provides
    @Singleton
    fun provideNewsRepository(
        dao: NewsDao,
    ): NewsRepository {
        return NewsRepositoryImpl(
            dao = dao
        )
    }
}