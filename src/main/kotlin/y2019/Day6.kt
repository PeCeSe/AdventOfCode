package y2019

import java.io.File

/*
What is the total number of direct and indirect orbits in your map data?
 */

class Day6 {


    fun getInput(): MutableList<String> {
        return File("input/y2019/day6.txt")
            .readLines()
            .toMutableList()
    }

    fun countOrbits(input: MutableList<String>): Int {
        val orbits = convertInputToOrbits(input)

        return orbits.map { it.getNumStepsToCom(orbits) }.sum()

    }

    fun getShortestPathBetweenOrbits(orbitName1: String, orbitName2: String, input: MutableList<String>): Int {
        val orbits = convertInputToOrbits(input)

        val orbit1 = orbits.find { it.name == orbitName1 }!!
        val orbit2 = orbits.find { it.name == orbitName2 }!!

        return orbit1.getShortestRouteToOrbit(orbit2, orbits).count()
    }

    private fun convertInputToOrbits(input: MutableList<String>): List<Orbit> {
       val orbits= input.map { it.split(')') }
            .map { Orbit(it.last(), it.first()) }
           .toMutableList()

        orbits.add(Orbit("COM", null))

        return orbits

    }

    data class Orbit(val name: String, val parent: String?) {

        fun getNumStepsToCom(orbits: List<Orbit>): Int{
            if(this.parent == null) {
                return 0
            }

            var counter = 0
            var orbit = this

            while(orbit.parent != null) {
                orbit = orbits.find { it.name == orbit.parent }!!
                counter ++
            }
            return counter
        }

        fun getShortestRouteToOrbit(destinationOrbit: Orbit, orbits: List<Orbit>): List<Orbit> {
            val route1 = this.getOrbitsToCom(orbits)
            val route2 = destinationOrbit.getOrbitsToCom(orbits)

            val duplicateRoute =  route1.intersect(route2).toList()

            return (route1 + route2).filter { !duplicateRoute.contains(it) }
        }

        fun getOrbitsToCom(orbits: List<Orbit>): List<Orbit> {
            if(this.parent == null) {
                return emptyList()
            }

            val orbitsPath = mutableListOf<Orbit>()
            var orbit = this

            while(orbit.parent != null) {
                orbit = orbits.find { it.name == orbit.parent }!!
                orbitsPath.add(orbit)
            }
            return orbitsPath
        }

    }
}

fun main() {

    val day6 = Day6()

    val input = day6.getInput()

    val result = day6.countOrbits(input)

    println("There are $result orbits")

    val result2 = day6.getShortestPathBetweenOrbits("YOU", "SAN", input)

    println("There are $result2 orbits between these orbits")
}
