package com.commandiron.tools_domain.use_cases

class CalculateCubicToSquare {
    operator fun invoke(cubicMeter: Double, thickness: Double) : Double {
        return Math.round(cubicMeter/(thickness/100) * 100.0) / 100.0
    }
}