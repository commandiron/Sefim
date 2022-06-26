package com.commandiron.tools_domain.use_cases

class ToolsUseCases(
    val insertAllTools: InsertAllTools,
    val getAllTools: GetAllTools,
    val getRecommendedTools: GetRecommendedTools,
    val favoriteTool: FavoriteTool,
    val getFavoriteTools: GetFavoriteTools,
)