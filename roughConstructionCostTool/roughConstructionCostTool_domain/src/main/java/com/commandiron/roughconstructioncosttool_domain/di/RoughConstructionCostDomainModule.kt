package com.commandiron.roughconstructioncosttool_domain.di

import com.commandiron.roughconstructioncosttool_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RoughConstructionCostDomainModule {

    @ViewModelScoped
    @Provides
    fun provideRoughConstructionCostUseCases(): RoughConstructionCostUseCases {
        return RoughConstructionCostUseCases(
            multiplyTwoDoubleValue = MultiplyTwoDoubleValue(),
            getFormWorkPrice = GetFormWorkPrice(),
            getRebarPrice = GetRebarPrice(),
            getConcretePrice = GetConcretePrice()
        )
    }
}