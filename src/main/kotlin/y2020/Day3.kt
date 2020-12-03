package y2020

import java.io.File

class Day3 {

    fun getInput(): List<String> {
        val input = mutableListOf<String>()
        File("input/y2020/day3.txt").forEachLine { input.add(it) }
        return input
    }

    fun findNumberOfTreesWithTrajectory(input: List<String>, trajectory: Pair<Int, Int>): Long {
        var xPos = trajectory.first
        var yPos = trajectory.second
        var treeCount = 0L

        while (yPos < input.size) {

            if( isTreeAtPosition(input, xPos, yPos)) {
                treeCount++
            }

            xPos += trajectory.first
            yPos += trajectory.second
        }

        return treeCount
    }

    private fun isTreeAtPosition(map: List<String>, xPos: Int, yPos: Int): Boolean {
        val line = map[yPos]
        var transformedXPos = xPos

        while(transformedXPos >= line.length) {
            transformedXPos -= line.length
        }

        return line[transformedXPos] == '#'
    }

    fun findProductOfAllGivenTrajectories(input: List<String>): Long {
        val allTrajectories = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)

        return allTrajectories.map { findNumberOfTreesWithTrajectory(input, it) }.fold(1L, { total, next -> total * next })
    }
}

fun main() {

    val day3 = Day3()

    val input = day3.getInput()
    val trajectory = 3 to 1

    // PART ONE
    val result = day3.findNumberOfTreesWithTrajectory(input, trajectory)
    println("There are $result trees")

    // PART TWO
    val result2 = day3.findProductOfAllGivenTrajectories(input)
    println("The product of all the trees are $result2")
}