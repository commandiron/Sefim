package com.commandiron.weathertool_data.remote

import com.commandiron.tools_data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
    ): WeatherDto

    companion object{
        const val BASE_URL = "https://api.openweathermap.org"
    }
}