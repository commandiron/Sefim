package com.commandiron.news_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.commandiron.news_data.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase: RoomDatabase(){
    abstract val dao: NewsDao
}