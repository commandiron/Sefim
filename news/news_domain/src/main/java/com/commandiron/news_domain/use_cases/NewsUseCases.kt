package com.commandiron.news_domain.use_cases

class NewsUseCases(
    val checkDatabaseIsExist: CheckDatabaseIsExist,
    val getAllNews: GetAllNews
)