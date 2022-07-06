package com.commandiron.news_domain.use_cases

import com.commandiron.core.model.News
import com.commandiron.news_domain.repository.NewsRepository

class GetAllNews(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<News> {
        return repository.getAllNews()
    }
}