package com.commandiron.rebarpricestool_domain.di

import com.commandiron.rebarpricestool_domain.repository.RebarPricesToolRepository
import com.commandiron.rebarpricestool_domain.use_cases.GetRebarPrices
import com.commandiron.rebarpricestool_domain.use_cases.RebarPricesToolUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RebarPricesToolDomainModule {

    @ViewModelScoped
    @Provides
    fun provideRebarPricesToolUseCases(
        repository: RebarPricesToolRepository
    ): RebarPricesToolUseCases {
        return RebarPricesToolUseCases(
            getRebarPrices = GetRebarPrices(repository)
        )
    }
}