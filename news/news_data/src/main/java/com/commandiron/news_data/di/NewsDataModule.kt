package com.commandiron.news_data.di

import android.app.Application
import androidx.room.Room
import com.commandiron.news_data.local.NewsDatabase
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
    fun provideNewsDatabase(app: Application): NewsDatabase{
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        db: NewsDatabase,
    ): NewsRepository {
        return NewsRepositoryImpl(
            dao = db.dao
        )
    }
}