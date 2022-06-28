package com.commandiron.news_domain.use_cases

import com.commandiron.news_domain.model.NewsContentPresentation
import com.commandiron.news_domain.repository.NewsRepository

class GetAllNews(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): NewsContentPresentation {
        return repository.getAllNews()
    }
}