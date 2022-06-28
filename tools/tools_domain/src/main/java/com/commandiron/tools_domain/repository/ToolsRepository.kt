package com.commandiron.tools_domain.repository

import android.location.Location
import com.commandiron.core.util.Response
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.WeatherPresentation
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface ToolsRepository {
    suspend fun insertAllTools(tools: List<Tool>)
    suspend fun getAllTools(): List<Tool>
    suspend fun insertTool(tool: Tool): Flow<Response<Unit>>
    suspend fun getUserLastKnownPosition(): Flow<Response<Location>>
    fun getCityAndCountryNameFromLatLng(latLng: LatLng): String
    suspend fun getWeather(latitude: String, longitude: String) : Flow<Response<WeatherPresentation>>
}