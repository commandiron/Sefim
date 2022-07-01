package com.commandiron.news_data.local

import androidx.room.*
import com.commandiron.news_data.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(newsEntity: List<NewsEntity>)

    @Query(" SELECT * FROM newsentity")
    suspend fun getAllNews(): List<NewsEntity>
}