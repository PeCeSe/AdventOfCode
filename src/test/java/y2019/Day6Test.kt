package y2019

import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

class Day6Test {

    private val day6 = Day6()

    @Test
    fun countNumOrbits_example1() {
        val result = day6.countOrbits(
            "COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L".split(",").toMutableList()
        )

        assertEquals(42, result)
    }

    @Test
    fun countNumOrbitBetweenYouAndSanta_example1() {
        val result = day6.getShortestPathBetweenOrbits(
            "YOU", "SAN",
            "COM)B,B)C,C)D,D)E,E)F,B)G,G)H,D)I,E)J,J)K,K)L,K)YOU,I)SAN".split(",").toMutableList()
        )

        assertEquals(4, result)
    }
}