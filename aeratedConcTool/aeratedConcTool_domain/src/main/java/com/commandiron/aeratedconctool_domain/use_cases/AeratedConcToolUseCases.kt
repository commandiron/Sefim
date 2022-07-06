package com.commandiron.aeratedconctool_domain.use_cases

class AeratedConcToolUseCases(
    val calculatePieceToPallet: CalculatePieceToPallet,
    val calculatePalletToPiece: CalculatePalletToPiece,
    val calculateSquareMeterToPallet: CalculateSquareMeterToPallet,
    val calculatePalletToSquareMeter: CalculatePalletToSquareMeter,
    val calculateCubicMeterToPallet: CalculateCubicMeterToPallet,
    val calculatePalletToCubicMeter: CalculatePalletToCubicMeter,
    val calculateSquareToCubic: CalculateSquareToCubic,
    val calculateCubicToSquare: CalculateCubicToSquare,
)