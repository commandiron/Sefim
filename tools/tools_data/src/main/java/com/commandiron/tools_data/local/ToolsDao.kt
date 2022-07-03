package com.commandiron.tools_data.local

import androidx.room.*
import com.commandiron.tools_data.local.entity.ToolEntity

@Dao
interface ToolsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTools(toolEntities: List<ToolEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTool(toolEntity: ToolEntity)

    @Query(" SELECT * FROM toolentity")
    suspend fun getAllTools(): List<ToolEntity>
}