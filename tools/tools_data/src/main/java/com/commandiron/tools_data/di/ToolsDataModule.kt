package com.commandiron.tools_data.di

import android.app.Application
import androidx.room.Room
import com.commandiron.tools_data.local.ToolsDatabase
import com.commandiron.tools_data.repository.ToolsRepositoryImpl
import com.commandiron.tools_domain.repository.ToolsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToolsDataModule {

    @Provides
    @Singleton
    fun provideToolsDatabase(app: Application): ToolsDatabase{
        return Room.databaseBuilder(
            app,
            ToolsDatabase::class.java,
            "tools_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToolsRepository(
        db: ToolsDatabase,
    ): ToolsRepository {
        return ToolsRepositoryImpl(
            dao = db.dao
        )
    }
}