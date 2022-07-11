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
    val increaseToolQuery: IncreaseToolQuery,
    val decreaseToolQuery: DecreaseToolQuery
)