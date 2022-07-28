package com.commandiron.rebarcalculatortool_domain.use_cases

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToLong

class CalculateRebarWeightTest {

    private lateinit var calculateRebarWeight: CalculateRebarWeight

    @Before
    fun setUp(){
        calculateRebarWeight = CalculateRebarWeight()
    }

    @Test
    fun `Rebar Weight value properly calculated`() {
        val diameters = listOf(
            8.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 24.0, 25.0, 26.0, 28.0, 30.0, 32.0, 36.0,
            40.0
        )
        val weights = listOf(0.395,0.617,0.888,1.208,1.578,1.998,2.466,2.984,3.551,3.853,4.168,4.834,
            5.549, 6.313,7.990,9.865
        )

        for(i in diameters.indices){
            val piece = 1
            val length = 1.0
            val diameter = diameters[i]
            val weight = weights[i]
            val result = calculateRebarWeight(
                piece = piece,
                length = length,
                diameter = diameter
            )
            val expectedResult = ((piece * length * weight * 1000.0).roundToLong() / 1000.0)
            Truth.assertThat(result).isEqualTo(expectedResult)
        }
    }
}