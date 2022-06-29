package com.commandiron.tools_presentation.aeratedConcTool

data class AeratedConcToolState(
    val piecePallet: String = "",
    val piecePalletLabel: String = "Adet",
    val piecePalletSymbol: String = " Adet",

    val thickness: String = "",
    val thicknessLabel: String = "Kalınlık",
    val thicknessSymbol: String = " cm",
    val thicknessList: List<String> = defaultThicknessList,
    val thicknessDropDownIsExpanded: Boolean = false,

    val result: String = "",
    val resultSymbol: String = "Palet",
    val unitConverterIsChanged: Boolean = false,
)

private val defaultThicknessList =
    listOf("8.5", "9", "10", "12.5", "13.5", "15", "17.5", "19", "20", "22.5", "25", "27.5",
        "30", "32.5", "35", "40")
