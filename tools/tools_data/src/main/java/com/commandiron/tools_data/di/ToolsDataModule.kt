package com.commandiron.tools_data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.commandiron.tools_data.BuildConfig
import com.commandiron.tools_data.local.ToolsDatabase
import com.commandiron.tools_data.remote.OpenWeatherApi
import com.commandiron.tools_data.remote.OpenWeatherApi.Companion.BASE_URL
import com.commandiron.tools_data.repository.ToolsRepositoryImpl
import com.commandiron.tools_domain.repository.ToolsRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val originalRequest = chain.request()
            val newHttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY)
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
        api: OpenWeatherApi,
        fusedLocationClient: FusedLocationProviderClient
    ): ToolsRepository {
        return ToolsRepositoryImpl(
            dao = db.dao,
            api = api,
            fusedLocationClient = fusedLocationClient
        )
    }
}