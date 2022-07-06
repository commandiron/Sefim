package com.commandiron.rebarcalculatortool_domain.di

import com.commandiron.rebarcalculatortool_domain.use_cases.CalculateRebarWeight
import com.commandiron.rebarcalculatortool_domain.use_cases.RebarCalculatorToolUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RebarCalculatorToolDomainModule {

    @ViewModelScoped
    @Provides
    fun provideRebarCalculatorToolUseCases(
    ): RebarCalculatorToolUseCases {
        return RebarCalculatorToolUseCases(
            calculateRebarWeight = CalculateRebarWeight()
        )
    }
}