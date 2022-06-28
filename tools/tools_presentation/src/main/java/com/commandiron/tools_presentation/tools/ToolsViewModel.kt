package com.commandiron.tools_presentation.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.UiEvent
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ToolsViewModel @Inject constructor(
    private val toolsUseCases: ToolsUseCases,
): ViewModel() {

    var state by mutableStateOf(ToolsState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(
                filteredTools = toolsUseCases.getAllTools(),
                allTools = toolsUseCases.getAllTools()
            )
        }
    }

    fun onEvent(userEvent: ToolsUserEvent) {
        when (userEvent) {
            is ToolsUserEvent.Favorite -> {
                viewModelScope.launch {
                    if(userEvent.tool.isFavorite){
                        toolsUseCases.unFavoriteTool(userEvent.tool).onEach{}.collect()
                    }else{
                        if(userEvent.tool.toolTags.contains(ToolTag.SOON)){
                            //Yakında gelecek snackbar
                        }else if (userEvent.tool.toolTags.contains(ToolTag.LOCKED)){
                            //Kilitli, açmak için kayıt olunuz, alert dialog.
                        }else{
                            toolsUseCases.favoriteTool(userEvent.tool).onEach{}.collect()
                        }
                    }
                    state = state.copy(
                        filteredTools = toolsUseCases.getAllTools()
                    )
                }
            }
            is ToolsUserEvent.IconClick -> {
                if(!userEvent.tool.toolTags.contains(ToolTag.SOON)){
                    sendUiEvent(UiEvent.Navigate(userEvent.tool.route))
                }else{
                    //Yakında gelecek.
                }
            }
            is ToolsUserEvent.SearchChange -> {
                state = state.copy(searchText = userEvent.text)
                state = state.copy(
                    filteredTools = toolsUseCases
                        .filterTools(
                            query = userEvent.text,
                            tools = state.allTools ?: listOf()
                        )
                )
            }
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}