package com.commandiron.tools_domain.use_cases

class ToolsUseCases(
    val prePopulateAllToolsInToolsDb: PrepopulateAllToolsInToolsDb,
    val getAllTools: GetAllTools,
    val getNewestTool: GetNewestTool,
    val getRecommendedTools: GetRecommendedTools,
    val favoriteTool: FavoriteTool,
    val unFavoriteTool: UnFavoriteTool,
    val getFavoriteTools: GetFavoriteTools,
    val filterTools: FilterTools,
    val getUserLastKnownPosition: GetUserLastKnownPosition,
    val getLatLngFromLocation: GetLatLngFromLocation,
    val getCityFromLatLng: GetCityFromLatLng,
    val getWeather: GetWeather,
    val calculatePieceToPallet: CalculatePieceToPallet,
    val calculatePalletToPiece: CalculatePalletToPiece,
    val calculateSquareMeterToPallet: CalculateSquareMeterToPallet,
    val calculatePalletToSquareMeter: CalculatePalletToSquareMeter,
    val calculateCubicMeterToPallet: CalculateCubicMeterToPallet,
    val calculatePalletToCubicMeter: CalculatePalletToCubicMeter,
    val calculateSquareToCubic: CalculateSquareToCubic,
    val calculateCubicToSquare: CalculateCubicToSquare,
    val getRebarPrices: GetRebarPrices,
    val calculateRebarWeight: CalculateRebarWeight
)