package com.commandiron.tools_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.commandiron.tools_data.local.entity.ToolEntity

@Database(
    entities = [ToolEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ToolsDatabase: RoomDatabase(){
    abstract val dao: ToolsDao
}