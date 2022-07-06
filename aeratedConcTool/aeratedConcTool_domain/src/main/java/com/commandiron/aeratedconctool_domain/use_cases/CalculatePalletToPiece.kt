package com.commandiron.aeratedconctool_domain.use_cases

class CalculatePalletToPiece {
    operator fun invoke(pallet: Int, thickness: Double) : Int {
        var unit = 1
        when(thickness){
            8.5 -> unit = 84
            9.0 -> unit = 78
            10.0 -> unit = 72
            12.5 -> unit = 60
            13.5 -> unit = 54
            15.0 -> unit = 48
            17.5 -> unit = 42
            19.0 -> unit = 36
            20.0 -> unit = 36
            22.5 -> unit = 30
            25.0 -> unit = 30
            27.5 -> unit = 24
            30.0 -> unit = 24
            32.5 -> unit = 18
            35.5 -> unit = 18
            40.0 -> unit = 18
        }
        return pallet * unit
    }
}