package y2021

import java.io.File

class Day2 {

    fun getInput(): List<Pair<String, Int>> {
        val input = mutableListOf<Pair<String, Int>>()
        File("input/y2021/day2.txt").forEachLine {
            val value = it.split(' ')
            input.add(value[0] to value[1].toInt())
        }
        return input
    }

    fun calculatePosition(input: List<Pair<String, Int>>): Pair<Int, Int> {
        var hPos = 0
        var depth = 0

        input.forEach {
            when (it.first) {
                "forward" -> hPos += it.second
                "down" -> depth += it.second
                "up" -> depth -= it.second
            }
        }
        return Pair(hPos, depth)
    }

    fun calculatePositionWithAim(input: List<Pair<String, Int>>): Pair<Int, Int> {
        var hPos = 0
        var depth = 0
        var aim = 0

        input.forEach {
            when (it.first) {
                "forward" -> {
                    hPos += it.second
                    depth += (aim * it.second)
                }
                "down" -> aim += it.second
                "up" -> aim -= it.second
            }
        }
        return Pair(hPos, depth)
    }

}

fun main() {

    val day2 = Day2()

    val input = day2.getInput()

    // PART ONE
    val position = day2.calculatePosition(input)
    println("Horizontal position: ${position.first}, depth: ${position.second}. Multiplied: ${position.first * position.second}")

    // PART TWO
    val aimPosition = day2.calculatePositionWithAim(input)
    println("Horizontal position: ${aimPosition.first}, depth: ${aimPosition.second}. Multiplied: ${aimPosition.first * aimPosition.second}")

}