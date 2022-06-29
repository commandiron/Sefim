package com.commandiron.tools_domain.use_cases

class ToolsUseCases(
    val prePopulateAllToolsInToolsDb: PrepopulateAllToolsInToolsDb,
    val getAllTools: GetAllTools,
    val getRecommendedTools: GetRecommendedTools,
    val favoriteTool: FavoriteTool,
    val unFavoriteTool: UnFavoriteTool,
    val getFavoriteTools: GetFavoriteTools,
    val filterTools: FilterTools,
    val getUserLastKnownPosition: GetUserLastKnownPosition,
    val getLatLngFromLocation: GetLatLngFromLocation,
    val getCityFromLatLng: GetCityFromLatLng,
    val getWeather: GetWeather,
    val calculateUnitToPallet: CalculateUnitToPallet,
    val calculatePalletToUnit: CalculatePalletToUnit
)