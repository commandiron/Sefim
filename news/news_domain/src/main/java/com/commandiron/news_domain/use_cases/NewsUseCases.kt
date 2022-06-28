package com.commandiron.news_domain.use_cases

class NewsUseCases(
    val prepopulateAllNewsIntoNewsDb: PrepopulateAllNewsIntoNewsDb,
    val getAllNews: GetAllNews
)