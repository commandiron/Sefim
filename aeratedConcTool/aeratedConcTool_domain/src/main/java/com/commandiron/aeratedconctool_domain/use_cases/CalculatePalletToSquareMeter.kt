package com.commandiron.aeratedconctool_domain.use_cases

class CalculatePalletToSquareMeter {
    operator fun invoke(pallet: Double, thickness: Double) : Double {
        var pieceInPallet = 1
        when(thickness){
            8.5 -> pieceInPallet = 84
            9.0 -> pieceInPallet = 78
            10.0 -> pieceInPallet = 72
            12.5 -> pieceInPallet = 60
            13.5 -> pieceInPallet = 54
            15.0 -> pieceInPallet = 48
            17.5 -> pieceInPallet = 42
            19.0 -> pieceInPallet = 36
            20.0 -> pieceInPallet = 36
            22.5 -> pieceInPallet = 30
            25.0 -> pieceInPallet = 30
            27.5 -> pieceInPallet = 24
            30.0 -> pieceInPallet = 24
            32.5 -> pieceInPallet = 18
            35.5 -> pieceInPallet = 18
            40.0 -> pieceInPallet = 18
        }
        return pallet * pieceInPallet * 0.6 * 0.25
    }
}