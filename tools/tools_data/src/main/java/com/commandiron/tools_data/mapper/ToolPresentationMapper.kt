package com.commandiron.tools_data.mapper

import com.commandiron.tools_data.local.entity.ToolEntity
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.model.ToolTag
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun ToolEntity.toTool(): Tool {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, ToolTag::class.java)
    val jsonAdapter: JsonAdapter<List<ToolTag>> = moshi.adapter(type)
    return Tool(
        id = id,
        title = title,
        resources = resources,
        isFavorite = isFavorite,
        toolTags = jsonAdapter.fromJson(toolTags) ?: listOf(),
        route = route
    )
}

fun Tool.toToolEntity(): ToolEntity {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, ToolTag::class.java)
    val jsonAdapter: JsonAdapter<List<ToolTag>> = moshi.adapter(type)
    return ToolEntity(
        id = id,
        title = title,
        resources = resources,
        isFavorite = isFavorite,
        toolTags = jsonAdapter.toJson(toolTags),
        route = route
    )
}