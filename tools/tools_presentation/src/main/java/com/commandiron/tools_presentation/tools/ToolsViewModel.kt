package com.commandiron.tools_presentation.tools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core_ui.util.Strings.Turkish.LOCKED
import com.commandiron.core_ui.util.Strings.Turkish.SOON_THREE_DOT
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.core.util.Response
import com.commandiron.core_ui.util.Strings.Turkish.ADDED_TO_FAVORITES
import com.commandiron.core_ui.util.Strings.Turkish.REMOVED_FROM_FAVORITES
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
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

    var getAllToolsJob: Job = Job()

    init {
        refresh()
    }

    fun onEvent(userEvent: ToolsUserEvent) {
        when (userEvent) {
            is ToolsUserEvent.Favorite -> {
                viewModelScope.launch {
                    if(userEvent.tool.isFavorite){
                        toolsUseCases.unFavoriteTool(userEvent.tool).collect{ response ->
                            when(response){
                                is Response.Error -> {}
                                Response.Loading -> {}
                                is Response.Success -> {
                                    refresh()
                                    sendUiEvent(UiEvent.ShowSnackbar(REMOVED_FROM_FAVORITES))
                                }
                            }
                        }
                    }else{
                        if(userEvent.tool.toolTags.contains(ToolTag.SOON)){
                            sendUiEvent(UiEvent.ShowSnackbar(SOON_THREE_DOT))
                        }else if (userEvent.tool.toolTags.contains(ToolTag.LOCKED)){
                            sendUiEvent(UiEvent.ShowSnackbar(LOCKED))
                        }else{
                            val queue = state.allTools?.filter { it.isFavorite }?.size ?: 0
                            toolsUseCases.favoriteTool(userEvent.tool.copy(queue = queue)).collect{ response ->
                                when(response){
                                    is Response.Error -> {}
                                    Response.Loading -> {}
                                    is Response.Success -> {
                                        refresh()
                                        sendUiEvent(UiEvent.ShowSnackbar(ADDED_TO_FAVORITES))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            is ToolsUserEvent.IconClick -> {
                if(!userEvent.tool.toolTags.contains(ToolTag.SOON)){
                    sendUiEvent(UiEvent.Navigate(userEvent.tool.route))
                }else{
                    sendUiEvent(UiEvent.ShowSnackbar(SOON_THREE_DOT))
                }
            }
            is ToolsUserEvent.SearchChange -> {
                state = state.copy(searchText = userEvent.text)
                filterTools(userEvent.text)
            }
            ToolsUserEvent.OnKeyboardDone -> {
                sendUiEvent(UiEvent.HideKeyboard)
            }
        }
    }

    private fun refresh(){
        getAllTools()
        getAllToolsJob.invokeOnCompletion {
            filterTools(state.searchText)
        }
    }

    private fun getAllTools(){
        getAllToolsJob = viewModelScope.launch {
            val allTools = toolsUseCases.getAllTools()
            state = state.copy(allTools = allTools)
        }
    }

    private fun filterTools(query: String){
        val filteredTools = toolsUseCases.filterTools(query, state.allTools ?: listOf())
        state = state.copy(filteredTools = filteredTools)
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch() {
            _uiEvent.send(uiEvent)
        }
    }
}