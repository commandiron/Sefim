package com.commandiron.tools_presentation.rebarCalculatorTool.model

data class RebarCalculatorItem(
    val pieceValue: String = "1",
    val pieceLabel: String = "Adet",
    val pieceUnit: String = "Adet",
    val lengthValue: String = "1",
    val lengthLabel: String = "Uzunluk",
    val lengthUnit: String = "m",
    val diameterValue: String = "",
    val diameterLabel: String = "Çap",
    val diameterUnit: String = "cm",
    val dropDownIsExpanded: Boolean = false,
    val dropDownItems: List<String> =
        listOf("Φ8", "Φ10", "Φ12", "Φ14", "Φ16",
            "Φ18", "Φ20", "Φ22", "Φ24", "Φ25",
            "Φ26", "Φ28", "Φ30", "Φ32", "Φ36",
            "Φ40"
        ),
    val resultText: String = "--",
    val resultUnit: String = "kg"
)
