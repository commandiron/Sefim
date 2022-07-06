package com.commandiron.aeratedconctool_domain.use_cases

import kotlin.math.roundToInt

class CalculateSquareToCubic {
    operator fun invoke(squareMeter: Double, thickness: Double) : Double {
        return (squareMeter * thickness / 100 * 100.0).roundToInt() / 100.0
    }
}