package com.commandiron.aeratedconctool_domain.use_cases

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToInt

class CalculateSquareMeterToPalletTest {

    private lateinit var calculateSquareMeterToPallet: CalculateSquareMeterToPallet

    @Before
    fun setUp(){
        calculateSquareMeterToPallet = CalculateSquareMeterToPallet()
    }

    @Test
    fun `Aerated concrete piece value to pallet value properly calculated`() {
        val cubicMetersInPallet = listOf(
            1.071, 1.053, 1.08, 1.125, 1.0935, 1.08, 1.1025, 1.026, 1.08, 1.0125, 1.125, 0.99, 1.08,
            0.8775, 0.945, 1.08
        )
        val thicknesses = listOf(
            8.5, 9.0, 10.0, 12.5, 13.5, 15.0, 17.5, 19.0, 20.0, 22.5, 25.0, 27.5, 30.0, 32.5, 35.5,
            40.0,
        )
        for(i in thicknesses.indices){
            val squareMeter = 1000.0
            val cubicMeterInPallet = cubicMetersInPallet[i]
            val thickness = thicknesses[i]
            val result = calculateSquareMeterToPallet(
                squareMeter = squareMeter,
                thickness = thickness
            )
            val expectedResult =(squareMeter * thickness / 100 / cubicMeterInPallet * 100.0).roundToInt() / 100.0
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }
}