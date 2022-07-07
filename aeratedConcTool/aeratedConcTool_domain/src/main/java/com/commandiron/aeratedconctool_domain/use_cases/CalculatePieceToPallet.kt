package com.commandiron.aeratedconctool_domain.use_cases

import kotlin.math.roundToInt

class CalculatePieceToPallet {
    operator fun invoke(piece: Int, thickness: Double) : Double {
        var cubicMeterInPallet = 1.0
        when(thickness){
            8.5 -> cubicMeterInPallet = 1.071
            9.0 -> cubicMeterInPallet = 1.053
            10.0 -> cubicMeterInPallet = 1.08
            12.5 -> cubicMeterInPallet = 1.125
            13.5 -> cubicMeterInPallet = 1.0935
            15.0 -> cubicMeterInPallet = 1.08
            17.5 -> cubicMeterInPallet = 1.1025
            19.0 -> cubicMeterInPallet = 1.026
            20.0 -> cubicMeterInPallet = 1.08
            22.5 -> cubicMeterInPallet = 1.0125
            25.0 -> cubicMeterInPallet = 1.125
            27.5 -> cubicMeterInPallet = 0.99
            30.0 -> cubicMeterInPallet = 1.08
            32.5 -> cubicMeterInPallet = 0.8775
            35.5 -> cubicMeterInPallet = 0.945
            40.0 -> cubicMeterInPallet = 1.08
        }
        return (piece * 0.6 * 0.25 * thickness / 100 / cubicMeterInPallet * 100.0).roundToInt() / 100.0
    }
}