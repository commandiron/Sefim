package com.commandiron.news_data.repository

import com.commandiron.news_data.local.NewsDao
import com.commandiron.news_data.mapper.toNews
import com.commandiron.news_data.mapper.toNewsEntity
import com.commandiron.news_domain.model.News
import com.commandiron.news_domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val dao: NewsDao
): NewsRepository {
    override suspend fun checkDatabaseIsExist(): Boolean {
        return dao.getAllNews().isNotEmpty()
    }

    override suspend fun getAllNews(): List<News> {
        return dao.getAllNews().map { it.toNews() }
    }
}