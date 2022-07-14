package com.commandiron.news_data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.commandiron.news_data.mapper.toNewsEntity
import com.commandiron.news_domain.model.newsList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider


class NewsCallback (
    private val provider: Provider<NewsDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateNews()
        }
    }

    private suspend fun populateNews() {
        provider.get().insertAllNews(newsList.map { it.toNewsEntity() })
    }
}