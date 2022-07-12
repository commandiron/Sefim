package com.commandiron.roughconstructioncosttool_domain.use_cases

import kotlin.math.roundToLong

class MultiplyTwoDoubleValue {
    operator fun invoke(firstValue: Double?, secondValue: Double?): Double? {
        firstValue?.let { firstValue ->
            secondValue?.let { secondValue ->
                return (firstValue * secondValue * 100.0).roundToLong() / 100.0
            }
        }
        return null
    }
}