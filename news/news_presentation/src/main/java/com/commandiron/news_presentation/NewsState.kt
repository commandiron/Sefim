package com.commandiron.news_presentation

import com.commandiron.news_domain.model.NewsContentPresentation

data class NewsState(
    val news: NewsContentPresentation? = null
)
