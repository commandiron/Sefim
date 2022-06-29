package com.commandiron.tools_domain.use_cases

class CalculateUnitToPallet {
    operator fun invoke(unit: Int, thickness: Double) : Double {
        var pallet = 1.0
        when(thickness){
            8.5 -> pallet = 1.071
            9.0 -> pallet = 1.053
            10.0 -> pallet = 1.08
            12.5 -> pallet = 1.125
            13.5 -> pallet = 1.0935
            15.0 -> pallet = 1.08
            17.5 -> pallet = 1.1025
            19.0 -> pallet = 1.026
            20.0 -> pallet = 1.08
            22.5 -> pallet = 1.0125
            25.0 -> pallet = 1.125
            27.5 -> pallet = 0.99
            30.0 -> pallet = 1.08
            32.5 -> pallet = 0.8775
            35.5 -> pallet = 0.945
            40.0 -> pallet = 1.08
        }
        return Math.round(unit*0.6*0.25*thickness/100/pallet * 10.0) / 10.0
    }
}