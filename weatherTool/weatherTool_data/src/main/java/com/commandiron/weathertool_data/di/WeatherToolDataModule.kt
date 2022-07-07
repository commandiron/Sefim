package com.commandiron.weathertool_data.di

import android.app.Application
import com.commandiron.weathertool_data.repository.WeatherToolRepositoryImpl
import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.commandiron.weathertool_data.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherToolDataModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    fun provideFusedLocationClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideWeatherToolRepository(
        api: WeatherApi,
        fusedLocationClient: FusedLocationProviderClient,
        app: Application
    ): WeatherToolRepository {
        return WeatherToolRepositoryImpl(
            api = api,
            fusedLocationClient = fusedLocationClient,
            application = app
        )
    }
}