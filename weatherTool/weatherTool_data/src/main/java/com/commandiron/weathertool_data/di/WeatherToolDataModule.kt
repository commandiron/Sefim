package com.commandiron.weathertool_data.di

import android.app.Application
import com.commandiron.core.BuildConfig
import com.commandiron.weathertool_data.remote.OpenWeatherApi
import com.commandiron.weathertool_data.remote.OpenWeatherApi.Companion.BASE_URL
import com.commandiron.weathertool_data.repository.WeatherToolRepositoryImpl
import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
object WeatherToolDataModule {

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
    fun provideFusedLocationClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideWeatherToolRepository(
        api: OpenWeatherApi,
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