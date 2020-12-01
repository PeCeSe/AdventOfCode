package y2019

import org.junit.Assert.assertEquals
import org.junit.Test

class Day8Test {

    private val day8 = Day8()

    @Test
    fun checkDataForCorruption_example1() {
        val result = day8.checkDataForCorruption("010212112011", 4, 1)

        assertEquals(3, result)
    }

    @Test
    fun checkDataForCorruption_example2() {
        val result = day8.checkDataForCorruption("222212010022", 2, 2)

        assertEquals(0, result)
    }

    @Test
    fun decodeImage_example1() {
        val result = day8.decodeImage("0222112222120000", 2, 2)
        val expected = listOf("01", "10")

        assertEquals(expected, result)
    }
}