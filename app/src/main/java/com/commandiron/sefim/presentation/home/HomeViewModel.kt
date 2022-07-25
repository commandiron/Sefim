package com.commandiron.sefim.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core_ui.util.Strings.Turkish.LOCKED
import com.commandiron.core_ui.util.Strings.Turkish.SOON_THREE_DOT
import com.commandiron.core_ui.util.Strings.Turkish.THIS_FEATURE_IS_NOT_ACTIVE_YET
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.sefim.navigation.NavigationItem
import com.commandiron.news_domain.use_cases.NewsUseCases
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.rebarpricestool_domain.use_cases.RebarPricesToolUseCases
import com.commandiron.tools_domain.model.Tool
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val toolsUseCases: ToolsUseCases,
    private val rebarPricesToolUseCases: RebarPricesToolUseCases
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        refreshData()
    }

    fun onEvent(userEvent: HomeUserEvent) {
        when (userEvent) {
            HomeUserEvent.EditClick -> {
                sendUiEvent(UiEvent.ShowSnackbar(THIS_FEATURE_IS_NOT_ACTIVE_YET))
            }
            HomeUserEvent.NotificationClick -> {
                sendUiEvent(UiEvent.ShowSnackbar(THIS_FEATURE_IS_NOT_ACTIVE_YET))
            }
            HomeUserEvent.ProfilePictureClick -> {
                sendUiEvent(UiEvent.ShowSnackbar(THIS_FEATURE_IS_NOT_ACTIVE_YET))
            }
            HomeUserEvent.AddClick -> {
                sendUiEvent(UiEvent.Navigate(NavigationItem.Tools.route))
            }
            is HomeUserEvent.IconClick -> {
                sendUiEvent(UiEvent.Navigate(userEvent.tool.route))
            }
            HomeUserEvent.ToolLongClick -> {
                state = state.copy(
                    isFavoriteIconsWobbling = true
                )
            }
            HomeUserEvent.SpaceClick -> {
                state = state.copy(
                    isFavoriteIconsWobbling = false
                )
            }
            is HomeUserEvent.UnFavoriteClick -> {
                viewModelScope.launch {
                    toolsUseCases.unFavoriteTool(userEvent.tool).collect{ response ->
                        when(response){
                            is Response.Error -> {}
                            Response.Loading -> {}
                            is Response.Success -> {
                                getFavoriteTools()
                                getRecommendedTools()
                            }
                        }
                    }
                }
            }
            is HomeUserEvent.FavoriteClick -> {
                if(userEvent.tool.toolTags.contains(ToolTag.SOON)){
                    sendUiEvent(UiEvent.ShowSnackbar(SOON_THREE_DOT))
                }else if (userEvent.tool.toolTags.contains(ToolTag.LOCKED)){
                    sendUiEvent(UiEvent.ShowSnackbar(LOCKED))
                }else{
                    viewModelScope.launch {
                        val queue = state.favoriteTools?.size ?: 0
                        toolsUseCases.favoriteTool(userEvent.tool.copy(queue = queue)).collect{ response ->
                            when(response){
                                is Response.Error -> {}
                                Response.Loading -> {}
                                is Response.Success -> {
                                    getFavoriteTools()
                                    getRecommendedTools()
                                }
                            }
                        }
                    }
                }
            }
            is HomeUserEvent.NewToolClick -> {
                sendUiEvent(UiEvent.Navigate(userEvent.tool.route))
            }
            is HomeUserEvent.NewsClick -> {
                sendUiEvent(UiEvent.Navigate(NavigationItem.News.route))
            }
            HomeUserEvent.RebarPriceClick -> {
                sendUiEvent(UiEvent.Navigate(NavigationItem.RebarPricesTool.route))
            }
            HomeUserEvent.NewsRefresh -> {
                refreshData()
            }
            is HomeUserEvent.ToolToLeft -> {
                toolToLeft(userEvent.tool)
            }
            is HomeUserEvent.ToolToRight -> {
                toolToRight(userEvent.tool)
            }
        }
    }

    private fun refreshData(){
        getFavoriteTools()
        getRecommendedTools()
        getRebarPrices()
        getNewestTool()
        getNewsList()
    }

    private fun getFavoriteTools(){
        viewModelScope.launch {
            val favoriteTools = toolsUseCases.getFavoriteTools()
            state = state.copy(favoriteTools = favoriteTools)
        }
    }

    private fun getRecommendedTools(){
        viewModelScope.launch {
            val recommendedTools = toolsUseCases.getRecommendedTools()
            state = state.copy(recommendedTools = recommendedTools)
        }
    }

    private fun getRebarPrices(){
        viewModelScope.launch {
            rebarPricesToolUseCases.getRebarPrices().collect{ response ->
                when(response){
                    is Response.Error -> {
                        sendUiEvent(UiEvent.ShowSnackbar(response.message))
                        state = state.copy(
                            newsIsLoading = false,
                            newsHasError = true
                        )
                    }
                    Response.Loading -> {
                        state = state.copy(
                            newsIsLoading = true,
                            newsHasError = false
                        )
                    }
                    is Response.Success -> {
                        val rebarPrice = response.data[1]
                        state = state.copy(
                            rebarPrice = rebarPrice,
                            newsIsLoading = false,
                            newsHasError = false
                        )
                    }
                }
            }
        }
    }

    private fun getNewestTool(){
        viewModelScope.launch() {
            val newestTool = toolsUseCases.getNewestTool()
            state = state.copy(newTool = newestTool)
        }
    }

    private fun getNewsList(){
        viewModelScope.launch {
            val allNews = newsUseCases.getAllNews()
            state = state.copy(newsList = allNews)
        }
    }

    private fun toolToLeft(tool: Tool){
        viewModelScope.launch {
            state.favoriteTools?.let { favoriteTools ->
                val increasedTool = favoriteTools.find { it.queue == tool.queue - 1 }
                increasedTool?.let { nonNullIncreasedTool ->
                    toolsUseCases.decreaseToolQuery(tool).collect{ response ->
                        when(response){
                            is Response.Error -> {}
                            Response.Loading -> {}
                            is Response.Success -> {}
                        }
                    }
                    toolsUseCases.increaseToolQuery(nonNullIncreasedTool).collect{ response ->
                        when(response){
                            is Response.Error -> {}
                            Response.Loading -> {}
                            is Response.Success -> {}
                        }
                    }
                    getFavoriteTools()
                }
            }
        }
    }

    private fun toolToRight(tool: Tool){
        viewModelScope.launch {
            state.favoriteTools?.let { favoriteTools ->
                val decreasedTool = favoriteTools.find { it.queue == tool.queue + 1 }
                decreasedTool?.let { nonNullDecreasedTool ->
                    toolsUseCases.increaseToolQuery(tool).collect{ response ->
                        when(response){
                            is Response.Error -> {}
                            Response.Loading -> {}
                            is Response.Success -> {}
                        }
                    }
                    toolsUseCases.decreaseToolQuery(nonNullDecreasedTool).collect{ response ->
                        when(response){
                            is Response.Error -> {}
                            Response.Loading -> {}
                            is Response.Success -> {}
                        }
                    }
                    getFavoriteTools()
                }
            }
        }
    }



    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch(Dispatchers.Main) {
            _uiEvent.send(uiEvent)
        }
    }
}