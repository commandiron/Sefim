package com.commandiron.news_data.repository

import com.commandiron.news_data.local.NewsDao
import com.commandiron.news_data.mapper.toNewsContentEntity
import com.commandiron.news_data.mapper.toNewsContentPresentation
import com.commandiron.news_domain.model.NewsContentPresentation
import com.commandiron.news_domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val dao: NewsDao
): NewsRepository {
    override suspend fun insertAllNews(news: NewsContentPresentation) {
        dao.insertAllNews(news.toNewsContentEntity())
    }

    override suspend fun getAllNews(): NewsContentPresentation {
        return dao.getAllNews().toNewsContentPresentation()
    }
}