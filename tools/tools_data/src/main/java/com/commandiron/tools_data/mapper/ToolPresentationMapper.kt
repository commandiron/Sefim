package com.commandiron.tools_data.mapper

import com.commandiron.tools_data.local.entity.ToolEntity
import com.commandiron.tools_domain.model.ToolPresentation
import com.commandiron.tools_domain.model.ToolTag
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun ToolEntity.toToolPresentation(): ToolPresentation {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, ToolTag::class.java)
    val jsonAdapter: JsonAdapter<List<ToolTag>> = moshi.adapter(type)
    return ToolPresentation(
        id = id,
        title = title,
        resources = resources,
        isFavorite = isFavorite,
        toolTags = jsonAdapter.fromJson(toolTags)
    )
}

fun ToolPresentation.toToolEntity(): ToolEntity {
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(List::class.java, ToolTag::class.java)
    val jsonAdapter: JsonAdapter<List<ToolTag>> = moshi.adapter(type)
    return ToolEntity(
        id = id,
        title = title,
        resources = resources,
        isFavorite = isFavorite,
        toolTags = jsonAdapter.toJson(toolTags)
    )
}