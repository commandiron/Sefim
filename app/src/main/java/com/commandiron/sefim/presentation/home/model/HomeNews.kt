package com.commandiron.sefim.presentation.home.model

import com.commandiron.news_domain.model.News
import com.commandiron.tools_domain.model.RebarPrice
import com.commandiron.tools_domain.model.Tool

data class HomeNews(
    val rebarPrice: RebarPrice? = null,
    val newTool: Tool? = null,
    val newsList: List<News> = listOf(),
)