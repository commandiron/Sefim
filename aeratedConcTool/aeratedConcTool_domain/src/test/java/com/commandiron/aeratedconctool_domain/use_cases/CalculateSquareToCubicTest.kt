package com.commandiron.aeratedconctool_domain.use_cases

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class CalculateSquareToCubicTest {

    private lateinit var calculateSquareToCubic: CalculateSquareToCubic

    @Before
    fun setUp(){
        calculateSquareToCubic = CalculateSquareToCubic()
    }

    @Test
    fun `Aerated concrete cubic meter value to square meter value properly calculated`() {
        val squareMeter = 12.6
        val thickness = 8.5
        val result = calculateSquareToCubic(
            squareMeter = squareMeter,
            thickness = thickness
        )
        val expectedResult = 1.07
        Truth.assertThat(result).isEqualTo(expectedResult)
    }
}