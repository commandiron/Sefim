package com.commandiron.tools_data.di

import android.app.Application
import androidx.room.Room
import com.commandiron.tools_data.local.ToolsDatabase
import com.commandiron.tools_data.remote.OpenWeatherApi
import com.commandiron.tools_data.remote.OpenWeatherApi.Companion.BASE_URL
import com.commandiron.tools_data.repository.ToolsRepositoryImpl
import com.commandiron.tools_domain.repository.ToolsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToolsDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val originalRequest = chain.request()
            val newHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("appid","84d2aaacca24c0faf9a390ddae080e27")
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()
            chain.proceed(newRequest)
        }
        httpClient.addNetworkInterceptor(logging)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(client: OkHttpClient): OpenWeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

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
        api: OpenWeatherApi
    ): ToolsRepository {
        return ToolsRepositoryImpl(
            dao = db.dao,
            api = api
        )
    }
}