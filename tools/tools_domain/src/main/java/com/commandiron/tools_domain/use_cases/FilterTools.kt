package com.commandiron.tools_domain.use_cases

import com.commandiron.core.model.Tool

class FilterTools {
    operator fun invoke(
        query: String,
        tools: List<Tool>
    ): List<Tool> {
        if(query.isEmpty()){
            return tools.sortedBy { it.id }
        }else{
            return tools.filter {
                it.title.contains(query.trim(), ignoreCase = true)
            }.sortedBy { it.id }
        }
    }
}