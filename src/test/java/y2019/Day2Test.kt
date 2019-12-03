package y2019

import org.junit.Test

import org.junit.Assert.*

class Day2Test {

    private val day2 = Day2()

    @Test
    fun processIntcode_example1() {
        val result = day2.processIntcode(mutableListOf(1,0,0,0,99))

        assertEquals(listOf(2,0,0,0,99), result)
    }

    @Test
    fun processIntcode_example2() {
        val result = day2.processIntcode(mutableListOf(2,3,0,3,99))

        assertEquals(listOf(2,3,0,6,99), result)
    }

    @Test
    fun processIntcode_example3() {
        val result = day2.processIntcode(mutableListOf(2,4,4,5,99,0))

        assertEquals(listOf(2,4,4,5,99,9801), result)
    }

    @Test
    fun processIntcode_example4() {
        val result = day2.processIntcode(mutableListOf(1,1,1,4,99,5,6,0,99))

        assertEquals(listOf(30,1,1,4,2,5,6,0,99), result)
    }
}