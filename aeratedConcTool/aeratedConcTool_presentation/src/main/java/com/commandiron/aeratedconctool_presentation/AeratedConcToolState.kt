package com.commandiron.aeratedconctool_presentation

data class AeratedConcToolState(

    val squareMetersPallet: String = "",
    val squareMetersPalletLabel: String = "m²",
    val squareMetersPalletUnit: String = "m²",
    val squareMetersPalletResult: String = "--",
    val squareMetersPalletResultUnit: String = "Palet",
    val squareMetersPalletUnitConverterIsChanged: Boolean = false,
    val squareMetersPalletThicknessDropDownIsExpanded: Boolean = false,

    val cubicMetersPallet: String = "",
    val cubicMetersPalletLabel: String = "m³",
    val cubicMetersPalletUnit: String = "m³",
    val cubicMetersPalletResult: String = "--",
    val cubicMetersPalletResultUnit: String = "Palet",
    val cubicMetersPalletUnitConverterIsChanged: Boolean = false,
    val cubicMetersPalletThicknessDropDownIsExpanded: Boolean = false,

    val piecePallet: String = "",
    val piecePalletLabel: String = "Adet",
    val piecePalletUnit: String = "Adet",
    val piecePalletResult: String = "--",
    val piecePalletResultUnit: String = "Palet",
    val piecePalletUnitConverterIsChanged: Boolean = false,
    val piecePalletThicknessDropDownIsExpanded: Boolean = false,

    val squareCubic: String = "",
    val squareCubicLabel: String = "m²",
    val squareCubicUnit: String = "m²",
    val squareCubicResult: String = "--",
    val squareCubicResultUnit: String = "m³",
    val squareCubicUnitConverterIsChanged: Boolean = false,
    val squareCubicThicknessDropDownIsExpanded: Boolean = false,

    val thickness: String = "",
    val thicknessLabel: String = "Kalınlık",
    val thicknessUnit: String = "cm",
    val thicknessList: List<String> = defaultThicknessList,
)

private val defaultThicknessList =
    listOf("8.5", "9", "10", "12.5", "13.5", "15", "17.5", "19", "20", "22.5", "25", "27.5",
        "30", "32.5", "35", "40")
