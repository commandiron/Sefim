package com.commandiron.sefim.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.commandiron.core.util.Response
import com.commandiron.core.util.UiEvent
import com.commandiron.sefim.navigation.NavigationItem
import com.commandiron.news_domain.use_cases.NewsUseCases
import com.commandiron.sefim.presentation.home.model.HomeNews
import com.commandiron.tools_domain.model.RebarPrice
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
                //Open Alert Dialog
                viewModelScope.launch {
                    toolsUseCases.unFavoriteTool(userEvent.tool).onEach {
                        when(it){
                            is Response.Error -> {
                                //Favorilere eklenemedi
                            }
                            Response.Loading -> {
                                //Loading
                            }
                            is Response.Success -> {
                                refreshData()
                            }
                        }
                    }.collect()
                }
            }
            is HomeUserEvent.FavoriteClick -> {
                if(userEvent.tool.toolTags.contains(ToolTag.SOON)){
                    //Yakında gelecek snackbar
                }else if (userEvent.tool.toolTags.contains(ToolTag.LOCKED)){
                    //Kilitli, açmak için kayıt olunuz, alert dialog.
                }else{
                    viewModelScope.launch {
                        toolsUseCases.favoriteTool(userEvent.tool).onEach {
                            when(it){
                                is Response.Error -> {
                                    //Favorilere eklenemedi
                                }
                                Response.Loading -> {
                                    //Loading
                                }
                                is Response.Success -> {
                                    refreshData()
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
        }
    }

    private fun refreshData(){
        viewModelScope.launch {
            state = state.copy(
                favoriteTools = toolsUseCases.getFavoriteTools(),
                homeNews = HomeNews(
                    newTool = toolsUseCases.getNewestTool(),
                    newsList = newsUseCases.getAllNews()
                ),
                recommendedTools = toolsUseCases.getRecommendedTools()
            )
        }
        getRebarPrices()
    }

    private fun getRebarPrices(){
        viewModelScope.launch {
            toolsUseCases.getRebarPrices().onEach { response ->
                when(response){
                    is Response.Error -> {
                    }
                    Response.Loading -> {
                    }
                    is Response.Success -> {
                        state = state.copy(
                            homeNews = state.homeNews?.copy(
                                rebarPrice = response.data[1]
                            ),
                        )
                    }
                }
            }.collect()
        }
    }

    private fun sendUiEvent(uiEvent: UiEvent){
        viewModelScope.launch(Dispatchers.Main) {
            _uiEvent.send(uiEvent)
        }
    }
}