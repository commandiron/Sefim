package com.commandiron.news_presentation

import com.commandiron.news_domain.model.News

data class NewsState(
    val news: List<News>? = null
)
