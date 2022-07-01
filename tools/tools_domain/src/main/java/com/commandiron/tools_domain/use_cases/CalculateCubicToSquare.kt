package com.commandiron.tools_domain.use_cases

import kotlin.math.roundToInt

class CalculateCubicToSquare {
    operator fun invoke(cubicMeter: Double, thickness: Double) : Double {
        return (cubicMeter / (thickness / 100) * 100.0).roundToInt() / 100.0
    }
}