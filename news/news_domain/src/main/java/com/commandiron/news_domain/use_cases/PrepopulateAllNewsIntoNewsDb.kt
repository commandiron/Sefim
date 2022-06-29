package com.commandiron.news_domain.use_cases

import com.commandiron.news_domain.model.*
import com.commandiron.news_domain.repository.NewsRepository

class PrepopulateAllNewsIntoNewsDb(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(){
        repository.insertAllNews(defaultNews)
    }
}