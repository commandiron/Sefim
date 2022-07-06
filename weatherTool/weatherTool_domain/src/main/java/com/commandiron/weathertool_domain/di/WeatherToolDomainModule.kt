package com.commandiron.weathertool_domain.di

import com.commandiron.weathertool_domain.repository.WeatherToolRepository
import com.commandiron.weathertool_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WeatherToolDomainModule {

    @ViewModelScoped
    @Provides
    fun provideWeatherToolUseCases(
        repository: WeatherToolRepository
    ): WeatherToolUseCases {
        return WeatherToolUseCases(
            getUserLastKnownPosition = GetUserLastKnownPosition(repository),
            getLatLngFromLocation = GetLatLngFromLocation(),
            getCityFromLatLng = GetCityFromLatLng(repository),
            getWeather = GetWeather(repository),
        )
    }
}