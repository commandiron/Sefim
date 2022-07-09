package com.commandiron.news_domain.repository

import com.commandiron.news_domain.model.News

interface NewsRepository {
    suspend fun insertAllNews(newsList: List<News>)
    suspend fun getAllNews(): List<News>
}