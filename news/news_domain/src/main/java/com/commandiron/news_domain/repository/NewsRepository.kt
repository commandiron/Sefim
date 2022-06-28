package com.commandiron.news_domain.repository

import com.commandiron.news_domain.model.NewsContentPresentation

interface NewsRepository {
    suspend fun insertAllNews(news: NewsContentPresentation)
    suspend fun getAllNews(): NewsContentPresentation
}