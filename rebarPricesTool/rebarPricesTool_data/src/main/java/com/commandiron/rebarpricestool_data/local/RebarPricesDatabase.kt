package com.commandiron.rebarpricestool_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.commandiron.rebarpricestool_data.local.entity.RebarPriceEntity

@Database(
    entities = [RebarPriceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RebarPricesDatabase: RoomDatabase(){
    abstract val dao: RebarPricesDao
}