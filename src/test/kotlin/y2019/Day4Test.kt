package y2019

import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

class Day4Test {

    private val day4 = Day4()

    @Test
    @Ignore
    fun findValidPasswordsWithinRange_example1() {
        val result = day4.findValidPasswordsWithinRangePart1(122..133)

        assertEquals(2, result)
    }

    @Test
    fun isValidPassword_example1() {
        val result = day4.isValidPasswordPart1(111111)

        assert(result)
    }

    @Test
    fun isValidPassword_example2() {
        val result = day4.isValidPasswordPart1(122345)

        assert(result)
    }

    @Test
    fun isValidPassword_example3() {
        val result = day4.isValidPasswordPart1(123456)

        assertFalse(result)
    }

    @Test
    fun isValidPassword_example4() {
        val result = day4.isValidPasswordPart1(654321)

        assertFalse(result)
    }
}