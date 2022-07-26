package com.commandiron.rebarcalculatortool_domain.use_cases

import kotlin.math.roundToLong

class CalculateRebarWeight {
    operator fun invoke(piece: Int, length: Double, diameter: Double) : Double {
        var weight = 0.395
        when(diameter){
            8.0 -> weight = 0.395
            10.0 -> weight = 0.617
            12.0 -> weight = 0.888
            14.0 -> weight = 1.208
            16.0 -> weight = 1.578
            18.0 -> weight = 1.998
            20.0 -> weight = 2.466
            22.0 -> weight = 2.984
            24.0 -> weight = 3.551
            25.0 -> weight = 3.853
            26.0 -> weight = 4.168
            28.0 -> weight = 4.834
            30.0 -> weight = 5.549
            32.0 -> weight = 6.313
            36.0 -> weight = 7.990
            40.0 -> weight = 9.865
        }
        return (piece * length * weight * 1000.0).roundToLong() / 1000.0
    }
}

