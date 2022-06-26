package com.commandiron.tools_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class ToolEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val resources : Int,
    val isFavorite: Boolean,
    val toolTags: String
)
