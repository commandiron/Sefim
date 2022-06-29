package com.commandiron.tools_presentation.aeratedConcTool

data class AeratedConcToolState(

    val squareMetersPallet: String = "",
    val squareMetersPalletLabel: String = "m2",
    val squareMetersPalletSymbol: String = " m2",
    val squareMetersPalletResult: String = "--",
    val squareMetersPalletResultSymbol: String = "Palet",
    val squareMetersPalletUnitConverterIsChanged: Boolean = false,
    val squareMetersPalletThicknessDropDownIsExpanded: Boolean = false,

    val cubicMetersPallet: String = "",
    val cubicMetersPalletLabel: String = "m3",
    val cubicMetersPalletSymbol: String = " m3",
    val cubicMetersPalletResult: String = "--",
    val cubicMetersPalletResultSymbol: String = "Palet",
    val cubicMetersPalletUnitConverterIsChanged: Boolean = false,
    val cubicMetersPalletThicknessDropDownIsExpanded: Boolean = false,

    val piecePallet: String = "",
    val piecePalletLabel: String = "Adet",
    val piecePalletSymbol: String = " Adet",
    val piecePalletResult: String = "--",
    val piecePalletResultSymbol: String = "Palet",
    val piecePalletUnitConverterIsChanged: Boolean = false,
    val piecePalletThicknessDropDownIsExpanded: Boolean = false,

    val thickness: String = "",
    val thicknessLabel: String = "Kalınlık",
    val thicknessSymbol: String = " cm",
    val thicknessList: List<String> = defaultThicknessList,
)

private val defaultThicknessList =
    listOf("8.5", "9", "10", "12.5", "13.5", "15", "17.5", "19", "20", "22.5", "25", "27.5",
        "30", "32.5", "35", "40")
