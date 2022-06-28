package com.commandiron.news_data.local

import androidx.room.*
import com.commandiron.news_data.local.entity.NewsContentEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(newsEntity: NewsContentEntity)

    @Query(" SELECT * FROM newscontententity")
    suspend fun getAllNews(): NewsContentEntity
}