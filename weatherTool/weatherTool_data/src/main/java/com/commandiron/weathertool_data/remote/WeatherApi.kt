package com.commandiron.weathertool_data.remote

import androidx.annotation.Keep
import retrofit2.http.GET
import retrofit2.http.Query

@Keep
interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}