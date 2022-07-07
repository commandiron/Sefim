package com.commandiron.aeratedconctool_domain.use_cases

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CalculateCubicToSquareTest {
    private lateinit var calculateCubicToSquare: CalculateCubicToSquare

    @Before
    fun setUp(){
        calculateCubicToSquare = CalculateCubicToSquare()
    }

    @Test
    fun `Aerated concrete cubic meter value to square meter value properly calculated`() {
        val cubicMeter = 1.071
        val thickness = 8.5
        val result = calculateCubicToSquare(
            cubicMeter = cubicMeter,
            thickness = thickness
        )
        val expectedResult = 12.6
        assertThat(result).isEqualTo(expectedResult)
    }
}