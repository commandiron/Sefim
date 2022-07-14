package com.commandiron.news_domain.model

data class News(
    val id: Int,
    val title: String
)

val newsList = listOf<News>(
)