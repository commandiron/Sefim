package com.commandiron.tools_data.mapper

import com.commandiron.tools_data.local.entity.ToolEntity
import com.commandiron.tools_domain.model.Tool

fun ToolEntity.toTool(): Tool {
    val toolFromId = Tool.toolFromId(id)
    return Tool(
        id = id,
        queue = queue,
        title = toolFromId.title,
        icon = toolFromId.icon,
        isFavorite = isFavorite,
        toolTags = toolFromId.toolTags,
        route = toolFromId.route
    )
}
fun Tool.toToolEntity(): ToolEntity {
    return ToolEntity(
        id = id,
        queue = queue,
        isFavorite = isFavorite,
    )
}