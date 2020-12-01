package y2019

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {

    private val day1 = Day1()

    // PART ONE
    @Test
    fun calculateNeededFuel_12_should_return_2() {
        val result = day1.calculateNeededFuel(12.0)

        assertEquals(2.0, result)
    }

    @Test
    fun calculateNeededFuel_14_should_return_2() {
        val result = day1.calculateNeededFuel(14.0)

        assertEquals(2.0, result)
    }

    @Test
    fun calculateNeededFuel_1969_should_return_654() {
        val result = day1.calculateNeededFuel(1969.0)

        assertEquals(654.0, result)
    }

    @Test
    fun calculateNeededFuel_100756_should_return_33853() {
        val result = day1.calculateNeededFuel(100756.0)

        assertEquals(33583.0, result)
    }

    // PART TWO
    @Test
    fun calculateFuelsFuel_14_should_return_2() {
        val result = day1.calculateFuelsFuel(14.0)

        assertEquals(2, result)
    }

    @Test
    fun calculateFuelsFuel_1969_should_return_966() {
        val result = day1.calculateFuelsFuel(1969.0)

        assertEquals(966, result)
    }

    @Test
    fun calculateFuelsFuel_100756_should_return_50346() {
        val result = day1.calculateFuelsFuel(100756.0)

        assertEquals(50346, result)
    }
}