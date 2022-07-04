package com.commandiron.sefim.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core_ui.util.Strings.Turkish.LOCKED
import com.commandiron.core_ui.util.Strings.Turkish.SOMETHING_WENT_WRONG
import com.commandiron.core_ui.util.Strings.Turkish.SOON_THREE_DOT
import com.commandiron.core_ui.util.Strings.Turkish.THIS_FEATURE_IS_NOT_ACTIVE_YET
import com.commandiron.core_ui.util.UiEvent
import com.commandiron.sefim.navigation.NavigationItem
import com.commandiron.news_domain.use_cases.NewsUseCases
import com.commandiron.tools_domain.model.ToolTag
import com.commandiron.tools_domain.use_cases.ToolsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases,
    private val toolsUseCases: ToolsUseCases,
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
                    toolsUseCases.unFavoriteTool(userEvent.tool).onEach {
                        when(it){
                            is Response.Error -> {
                                sendUiEvent(UiEvent.ShowSnackbar(SOMETHING_WENT_WRONG))
                            }
                            Response.Loading -> {}
                            is Response.Success -> {
                                getFavoriteTools()
                                getRecommendedTools()
                            }
                        }
                    }.collect()
                }
            }
            is HomeUserEvent.FavoriteClick -> {
                if(userEvent.tool.toolTags.contains(ToolTag.SOON)){
                    sendUiEvent(UiEvent.ShowSnackbar(SOON_THREE_DOT))
                }else if (userEvent.tool.toolTags.contains(ToolTag.LOCKED)){
                    sendUiEvent(UiEvent.ShowSnackbar(LOCKED))
                }else{
                    viewModelScope.launch {
                        toolsUseCases.favoriteTool(userEvent.tool).onEach {
                            when(it){
                                is Response.Error -> {
                                    sendUiEvent(UiEvent.ShowSnackbar(SOMETHING_WENT_WRONG))
                                }
                                Response.Loading -> {}
                                is Response.Success -> {
                                    getFavoriteTools()
                                    getRecommendedTools()
                                }
                            }
                        }.collect()
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
            toolsUseCases.getFavoriteTools().collect{ response ->
                when(response){
                    is Response.Error -> {
                        println("1")
                    }
                    Response.Loading -> {
                        println("2")
                    }
                    is Response.Success -> {
                        println("3:" + response.data)
                        state = state.copy(
                            favoriteTools = response.data
                        )
                    }
                }
            }
        }
    }

    private fun getRecommendedTools(){
        viewModelScope.launch {
            toolsUseCases.getRecommendedTools().collect{ response ->
                when(response){
                    is Response.Error -> {}
                    Response.Loading -> {}
                    is Response.Success -> {
                        state = state.copy(
                            recommendedTools = response.data
                        )
                    }
                }
            }
        }
    }

    private fun getRebarPrices(){
        viewModelScope.launch {
            toolsUseCases.getRebarPrices().collect{ response ->
                when(response){
                    is Response.Error -> {
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
                        state = state.copy(
                            homeNews = state.homeNews.copy(
                                rebarPrice = response.data[1],
                            ),
                            newsIsLoading = false,
                            newsHasError = false
                        )
                    }
                }
            }
        }
    }

    private fun getNewestTool(){
        viewModelScope.launch {
            toolsUseCases.getNewestTool().collect{ tool ->
                state = state.copy(
                    homeNews = state.homeNews.copy(
                        newTool = tool,
                    ),
                )
            }
        }
    }

    private fun getNewsList(){
        viewModelScope.launch {
            state = state.copy(
                homeNews = state.homeNews.copy(
                    newsList = newsUseCases.getAllNews()
                ),
            )
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch(Dispatchers.Main) {
            _uiEvent.send(uiEvent)
        }
    }
}