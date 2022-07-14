package com.commandiron.news_domain.use_cases

import com.commandiron.news_domain.repository.NewsRepository

class CheckDatabaseIsExist(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.checkDatabaseIsExist()

}