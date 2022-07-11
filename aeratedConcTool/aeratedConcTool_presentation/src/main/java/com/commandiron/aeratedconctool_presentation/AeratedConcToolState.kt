package com.commandiron.aeratedconctool_presentation

import com.commandiron.core_ui.util.Strings.CENTIMETER
import com.commandiron.core_ui.util.Strings.CUBIC_METER
import com.commandiron.core_ui.util.Strings.EMPTY_RESULT_DOUBLE_DASH
import com.commandiron.core_ui.util.Strings.EMPTY_STRING
import com.commandiron.core_ui.util.Strings.SQUARE_METER
import com.commandiron.core_ui.util.Strings.Turkish.PALLET
import com.commandiron.core_ui.util.Strings.Turkish.PIECE
import com.commandiron.core_ui.util.Strings.Turkish.THICKNESS

data class AeratedConcToolState(

    val squareMetersPallet: String = EMPTY_STRING,
    val squareMetersPalletLabel: String = SQUARE_METER,
    val squareMetersPalletUnit: String = SQUARE_METER,
    val squareMetersPalletResult: String = EMPTY_RESULT_DOUBLE_DASH,
    val squareMetersPalletResultUnit: String = PALLET,
    val squareMetersPalletUnitConverterIsChanged: Boolean = false,
    val squareMetersPalletThicknessDropDownIsExpanded: Boolean = false,

    val cubicMetersPallet: String = EMPTY_STRING,
    val cubicMetersPalletLabel: String = CUBIC_METER,
    val cubicMetersPalletUnit: String = CUBIC_METER,
    val cubicMetersPalletResult: String = EMPTY_RESULT_DOUBLE_DASH,
    val cubicMetersPalletResultUnit: String = PALLET,
    val cubicMetersPalletUnitConverterIsChanged: Boolean = false,
    val cubicMetersPalletThicknessDropDownIsExpanded: Boolean = false,

    val piecePallet: String = EMPTY_STRING,
    val piecePalletLabel: String = PIECE,
    val piecePalletUnit: String = PIECE,
    val piecePalletResult: String = EMPTY_RESULT_DOUBLE_DASH,
    val piecePalletResultUnit: String = PALLET,
    val piecePalletUnitConverterIsChanged: Boolean = false,
    val piecePalletThicknessDropDownIsExpanded: Boolean = false,

    val squareCubic: String = EMPTY_STRING,
    val squareCubicLabel: String = SQUARE_METER,
    val squareCubicUnit: String = SQUARE_METER,
    val squareCubicResult: String = EMPTY_RESULT_DOUBLE_DASH,
    val squareCubicResultUnit: String = CUBIC_METER,
    val squareCubicUnitConverterIsChanged: Boolean = false,
    val squareCubicThicknessDropDownIsExpanded: Boolean = false,

    val thickness: String = EMPTY_STRING,
    val thicknessLabel: String = THICKNESS,
    val thicknessUnit: String = CENTIMETER,
    val thicknessList: List<String> = defaultThicknessList,
)

private val defaultThicknessList =
    listOf("8.5", "9", "10", "12.5", "13.5", "15", "17.5", "19", "20", "22.5", "25", "27.5",
        "30", "32.5", "35", "40")
