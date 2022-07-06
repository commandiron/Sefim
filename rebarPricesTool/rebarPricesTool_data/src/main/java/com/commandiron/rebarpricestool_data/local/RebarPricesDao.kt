package com.commandiron.rebarpricestool_data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.commandiron.rebarpricestool_data.local.entity.RebarPriceEntity

@Dao
interface RebarPricesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRebarPrices(rebarPrices: List<RebarPriceEntity>)

    @Query(" SELECT * FROM rebarpriceentity")
    suspend fun getAllRebarPrices(): List<RebarPriceEntity>
}