package y2019

import java.io.File
import java.lang.Exception
import kotlin.math.abs

/*
What is the Manhattan distance from the central port to the closest intersection?

What is the fewest combined steps the wires must take to reach an intersection?
 */

class Day3 {

    fun getInput(): Pair<List<String>, List<String>> {
        val wires = File("input/y2019/day3.txt")
            .readLines()
            .map {
                it.split(',')
            }

        return Pair(wires[0], wires[1])
    }

    fun calculateManhattenDistance(wires: Pair<List<String>, List<String>> ): Int? {
        val coordinates = Pair(findCoordinates(wires.first), findCoordinates(wires.second))
        val intersections = coordinates.first.intersect(coordinates.second)

        return intersections.filter { !(it.first == 0 && it.second == 0) }.map { getManhattenDistance(it) }.minBy { abs(it) }
    }

    fun findFewestSteps(wires: Pair<List<String>, List<String>>): Int {
        val coordinates = Pair(findCoordinates(wires.first), findCoordinates(wires.second))
        val intersections = coordinates.first.intersect(coordinates.second).filter { !(it.first == 0 && it.second == 0) }

        val stepsToIntersections = intersections.map {
            (coordinates.first.indexOf(it) + coordinates.second.indexOf(it)) to it
        }

        return stepsToIntersections.sortedBy { it.first }.first().first
    }

    private fun findCoordinates(path: List<String>): List<Pair<Int, Int>> {
        var coordinates = listOf(0 to 0)

        path.forEach{
            val direction = it.first()
            val distance = it.substring(1).toInt()

            coordinates = move(distance, direction, coordinates)
        }

        return coordinates
    }

    private fun move(distance: Int, direction: Char, coordinates: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val result = coordinates.toMutableList()
        var newCoordinates = coordinates.last()

        (0 until distance).map {
            newCoordinates = when(direction){
                'U' -> newCoordinates.first to newCoordinates.second + 1
                'D' -> newCoordinates.first to newCoordinates.second - 1
                'R' -> newCoordinates.first + 1 to newCoordinates.second
                'L' -> newCoordinates.first - 1 to newCoordinates.second
                else -> throw Exception("Unknown direction $direction")
            }

            result.add(newCoordinates)
        }
        return result
    }

    private fun getManhattenDistance(coordinates: Pair<Int, Int>): Int {
        return abs(coordinates.first) + abs(coordinates.second)
    }
}

fun main() {

    val day3 = Day3()

    val input = day3.getInput()

    val manhatten = day3.calculateManhattenDistance(input)
    println("The minimum manhatten distance is: $manhatten")

    val steps = day3.findFewestSteps(input)
    println("The fewest steps to an intersection is: $steps")
}
