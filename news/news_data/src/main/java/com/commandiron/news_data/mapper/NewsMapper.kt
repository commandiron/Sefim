package com.commandiron.news_data.mapper

import com.commandiron.news_data.local.entity.NewsEntity
import com.commandiron.core.model.News

fun NewsEntity.toNews(): News {
    return News(
        id = id,
        title = title
    )
}
fun News.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        title = title
    )
}