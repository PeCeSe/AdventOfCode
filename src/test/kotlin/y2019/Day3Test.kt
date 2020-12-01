package y2019

import org.junit.Test

import org.junit.Assert.*

class Day3Test {

    private val day3 = Day3()

    @Test
    fun calculateManhattenDistance_example1() {
        val result = day3.calculateManhattenDistance(listOf("R8","U5","L5","D3") to listOf("U7","R6","D4","L4"))

        assertEquals(6, result)
    }

    @Test
    fun calculateManhattenDistance_example2() {
        val result = day3.calculateManhattenDistance("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(',') to "U62,R66,U55,R34,D71,R55,D58,R83".split(','))

        assertEquals(159, result)
    }

    @Test
    fun calculateManhattenDistance_example3() {
        val result = day3.calculateManhattenDistance("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(',') to "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(','))

        assertEquals(135, result)
    }

    @Test
    fun findFewestSteps_example1() {
        val result = day3.findFewestSteps("R75,D30,R83,U83,L12,D49,R71,U7,L72".split(',') to "U62,R66,U55,R34,D71,R55,D58,R83".split(','))

        assertEquals(610, result)
    }

    @Test
    fun findFewestSteps_exampl21() {
        val result = day3.findFewestSteps("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".split(',') to "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".split(','))

        assertEquals(410, result)
    }
}