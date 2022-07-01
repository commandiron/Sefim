package com.commandiron.news_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey
    val id: Int,
    val title: String
)
