package com.commandiron.tools_data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.commandiron.tools_data.local.entity.ToolEntity
import com.commandiron.tools_data.mapper.toToolEntity
import com.commandiron.tools_domain.R
import com.commandiron.tools_domain.model.*
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
        provider.get().insertAllTools(
            listOf(
                rebarPricesTool.toToolEntity(),
                weatherTool.toToolEntity(),
                aeratedConcTool.toToolEntity(),
                rebarCalculatorTool.toToolEntity(),
                roughConstructionCostCalculatorTool.toToolEntity(),
                lengthMeasureWithCamTool.toToolEntity()
            )
        )
    }
}