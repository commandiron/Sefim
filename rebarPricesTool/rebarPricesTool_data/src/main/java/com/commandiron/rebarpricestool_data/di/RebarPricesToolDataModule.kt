package com.commandiron.rebarpricestool_data.di

import android.app.Application
import androidx.room.Room
import com.commandiron.rebarpricestool_data.local.RebarPricesDatabase
import com.commandiron.rebarpricestool_data.repository.RebarPricesToolRepositoryImpl
import com.commandiron.rebarpricestool_domain.repository.RebarPricesToolRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RebarPricesToolDataModule {

    @Provides
    @Singleton
    fun provideRebarPricesDatabase(app: Application): RebarPricesDatabase{
        return Room.databaseBuilder(
            app,
            RebarPricesDatabase::class.java,
            "rebarPrices_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRebarPricesToolRepository(
        db: RebarPricesDatabase
    ): RebarPricesToolRepository {
        return RebarPricesToolRepositoryImpl(
            dao = db.dao
        )
    }
}