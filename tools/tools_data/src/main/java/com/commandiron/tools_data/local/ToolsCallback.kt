package com.commandiron.tools_data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_domain.model.allToolsInApp
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider


class ToolsCallback (
    private val provider: Provider<ToolsDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateTools()
        }
    }

    private suspend fun populateTools() {
        provider.get().insertAllTools(allToolsInApp.map { it.toToolEntity() })
    }
}