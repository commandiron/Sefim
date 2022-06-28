package com.commandiron.news_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.commandiron.news_domain.model.NewToolsNewsPresentation
import com.commandiron.news_domain.model.SectoralNewsPresentation
import com.commandiron.news_domain.model.SteelPriceNewsPresentation

@Entity
data class NewsContentEntity(
    @PrimaryKey
    val id: Int,
    val steelPriceNewsList: String,
    val newToolNewsList: String,
    val sectoralNewsList: String
)
