package com.commandiron.tools_domain.use_cases

class CalculateSquareToCubic {
    operator fun invoke(squareMeter: Double, thickness: Double) : Double {
        return Math.round(squareMeter*thickness/100 * 100.0) / 100.0
    }
}