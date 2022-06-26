package com.commandiron.news_domain.model

data class NewsContentPresentation(
    val steelPriceNewsList: List<SteelPriceNewsPresentation>? = null,
    val newToolNewsList: List<NewToolsNewsPresentation>? = null,
    val sectoralNewsList: List<SectoralNewsPresentation>? = null
)

data class SteelPriceNewsPresentation(
    val title: String,
    val q8mmPrice: String,
    val q10mmPrice: String,
    val q1232mmPrice: String,
)
data class NewToolsNewsPresentation(
    val title: String,
    val toolId: Int,
)
data class SectoralNewsPresentation(
    val title: String,
    val imageUrl: String
)