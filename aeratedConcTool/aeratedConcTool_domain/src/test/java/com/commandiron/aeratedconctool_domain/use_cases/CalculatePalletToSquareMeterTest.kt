package com.commandiron.aeratedconctool_domain.use_cases

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class CalculatePalletToSquareMeterTest {

    private lateinit var calculatePalletToSquareMeter: CalculatePalletToSquareMeter

    @Before
    fun setUp(){
        calculatePalletToSquareMeter = CalculatePalletToSquareMeter()
    }

    @Test
    fun `Aerated concrete pallet value to square meter value properly calculated`() {
        val piecesInPallet = listOf(
            84, 78, 72, 60, 54, 48, 42, 36, 36, 30, 30, 24, 24, 18, 18, 18
        )
        val thicknesses = listOf(
            8.5, 9.0, 10.0, 12.5, 13.5, 15.0, 17.5, 19.0, 20.0, 22.5, 25.0, 27.5, 30.0, 32.5, 35.5,
            40.0,
        )
        for(i in thicknesses.indices){
            val pallet = 100.0
            val pieceInPallet = piecesInPallet[i]
            val thickness = thicknesses[i]
            val result = calculatePalletToSquareMeter(
                pallet = pallet,
                thickness = thickness
            )
            Truth.assertThat(result).isEqualTo(pallet * pieceInPallet * 0.6 * 0.25)
        }
    }
}