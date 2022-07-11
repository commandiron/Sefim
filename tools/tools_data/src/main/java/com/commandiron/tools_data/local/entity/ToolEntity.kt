package com.commandiron.tools_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToolEntity(
    @PrimaryKey
    val id: Int,
    val queue: Int,
    val title: String,
    val resources : Int,
    val isFavorite: Boolean,
    val toolTags: String,
    val route: String
)
