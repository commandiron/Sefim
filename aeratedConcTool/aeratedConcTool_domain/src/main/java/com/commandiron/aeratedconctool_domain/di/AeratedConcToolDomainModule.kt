package com.commandiron.aeratedconctool_domain.di

import com.commandiron.aeratedconctool_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AeratedConcToolDomainModule {

    @ViewModelScoped
    @Provides
    fun provideAeratedConcToolUseCases(
    ): AeratedConcToolUseCases {
        return AeratedConcToolUseCases(
            calculatePieceToPallet = CalculatePieceToPallet(),
            calculatePalletToPiece = CalculatePalletToPiece(),
            calculateSquareMeterToPallet = CalculateSquareMeterToPallet(),
            calculatePalletToSquareMeter = CalculatePalletToSquareMeter(),
            calculateCubicMeterToPallet = CalculateCubicMeterToPallet(),
            calculatePalletToCubicMeter = CalculatePalletToCubicMeter(),
            calculateSquareToCubic = CalculateSquareToCubic(),
            calculateCubicToSquare = CalculateCubicToSquare(),
        )
    }
}