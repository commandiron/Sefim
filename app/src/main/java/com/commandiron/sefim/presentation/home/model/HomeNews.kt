package com.commandiron.sefim.presentation.home.model

import com.commandiron.news_domain.model.News
import com.commandiron.tools_domain.model.RebarPrice
import com.commandiron.tools_domain.model.Tool

data class HomeNews(
    val rebarPrice: RebarPrice,
    val newTool: Tool,
    val newsList: List<News>,
)