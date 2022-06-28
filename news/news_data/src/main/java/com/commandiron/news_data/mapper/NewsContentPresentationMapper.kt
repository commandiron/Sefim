package com.commandiron.news_data.mapper

import com.commandiron.news_data.local.entity.NewsContentEntity
import com.commandiron.news_domain.model.NewToolsNewsPresentation
import com.commandiron.news_domain.model.NewsContentPresentation
import com.commandiron.news_domain.model.SectoralNewsPresentation
import com.commandiron.news_domain.model.SteelPriceNewsPresentation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun NewsContentEntity.toNewsContentPresentation(): NewsContentPresentation {
    val moshi = Moshi.Builder().build()
    val typeSteelPrice = Types.newParameterizedType(List::class.java,SteelPriceNewsPresentation::class.java)
    val jsonAdapterSteelPrice: JsonAdapter<List<SteelPriceNewsPresentation>> = moshi.adapter(typeSteelPrice)
    val typeNewTools = Types.newParameterizedType(List::class.java,NewToolsNewsPresentation::class.java)
    val jsonAdapterNewTools: JsonAdapter<List<NewToolsNewsPresentation>> = moshi.adapter(typeNewTools)
    val typeSectoralNews = Types.newParameterizedType(List::class.java,SectoralNewsPresentation::class.java)
    val jsonAdapterSectoralNews: JsonAdapter<List<SectoralNewsPresentation>> = moshi.adapter(typeSectoralNews)
    return NewsContentPresentation(
        id = id,
        steelPriceNewsList = jsonAdapterSteelPrice.fromJson(steelPriceNewsList),
        newToolNewsList = jsonAdapterNewTools.fromJson(newToolNewsList),
        sectoralNewsList = jsonAdapterSectoralNews.fromJson(sectoralNewsList)
    )
}

fun NewsContentPresentation.toNewsContentEntity(): NewsContentEntity {
    val moshi = Moshi.Builder().build()
    val typeSteelPrice = Types.newParameterizedType(List::class.java,SteelPriceNewsPresentation::class.java)
    val jsonAdapterSteelPrice: JsonAdapter<List<SteelPriceNewsPresentation>> = moshi.adapter(typeSteelPrice)
    val typeNewTools = Types.newParameterizedType(List::class.java,NewToolsNewsPresentation::class.java)
    val jsonAdapterNewTools: JsonAdapter<List<NewToolsNewsPresentation>> = moshi.adapter(typeNewTools)
    val typeSectoralNews = Types.newParameterizedType(List::class.java,SectoralNewsPresentation::class.java)
    val jsonAdapterSectoralNews: JsonAdapter<List<SectoralNewsPresentation>> = moshi.adapter(typeSectoralNews)
    return NewsContentEntity(
        id = id,
        steelPriceNewsList = jsonAdapterSteelPrice.toJson(steelPriceNewsList),
        newToolNewsList = jsonAdapterNewTools.toJson(newToolNewsList),
        sectoralNewsList = jsonAdapterSectoralNews.toJson(sectoralNewsList)
    )
}