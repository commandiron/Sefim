package com.commandiron.tools_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToolEntity(
    @PrimaryKey
    val id: Int,
    val queue: Int,
    val isFavorite: Boolean,
)
