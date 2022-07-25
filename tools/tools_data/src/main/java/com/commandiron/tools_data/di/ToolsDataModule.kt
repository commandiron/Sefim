package com.commandiron.tools_data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.commandiron.tools_data.local.ToolsCallback
import com.commandiron.tools_data.local.ToolsDao
import com.commandiron.tools_data.local.ToolsDatabase
import com.commandiron.tools_data.repository.ToolsRepositoryImpl
import com.commandiron.tools_domain.repository.ToolsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToolsDataModule {

    @Provides
    @Singleton
    fun provideToolsDatabase(
        @ApplicationContext context: Context,
        provider: Provider<ToolsDao>
    ): ToolsDatabase{
        return Room.databaseBuilder(
            context,
            ToolsDatabase::class.java,
            "tools_db"
        )
            .addCallback(ToolsCallback(provider))
//            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideDao(database: ToolsDatabase) = database.dao

    @Provides
    @Singleton
    fun provideToolsRepository(
        dao: ToolsDao,
    ): ToolsRepository {
        return ToolsRepositoryImpl(
            dao = dao
        )
    }
}

//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("INSERT INTO toolentity VALUES (6,6,0) ")
//    }
//}