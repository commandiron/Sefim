package com.commandiron.tools_domain.di

import com.commandiron.tools_domain.repository.ToolsRepository
import com.commandiron.tools_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ToolsDomainModule {

    @ViewModelScoped
    @Provides
    fun provideToolsUseCases(
        repository: ToolsRepository
    ): ToolsUseCases {
        return ToolsUseCases(
            prePopulateAllToolsInToolsDb = PrepopulateAllToolsInToolsDb(repository),
            getAllTools = GetAllTools(repository),
            getRecommendedTools = GetRecommendedTools(repository),
            favoriteTool = FavoriteTool(repository),
            unFavoriteTool = UnFavoriteTool(repository),
            getFavoriteTools = GetFavoriteTools(repository),
            filterTools = FilterTools(),
            getWeather = GetWeather(repository)
        )
    }
}