package y2020

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {

    private val day1 = Day1()

    private val input = listOf<Int>(1, 2, 3, 4, 5, 6)

    // PART ONE
    @Test
    fun findTwoNumbersThatAddsToSum_should_return_correct_result() {
        val result = day1.findTwoNumbersThatAddsToSum(10, input)

        assertEquals(Pair(4, 6), result)
    }


    // PART TWO
    @Test
    fun findThreeNumbersThatAddsToSum_should_return_correct_result() {
        val result = day1.findThreeNumbersThatAddsToSum(6, input)

        assertEquals(Triple(1, 2, 3), result)
    }

}