package y2020

import java.io.File

class Day5 {

    fun getInput(): List<String> {
        val input = mutableListOf<String>()
        File("input/y2020/day5.txt").forEachLine { input.add(it) }
        return input
    }

    fun convertInputToSeatID(input: List<String>): List<Int> {
        val rows = (0..127).toList()
        val seats = (0..8).toList()

        return input
            .map { Pair(findPosition(rows, it.take(7)), findPosition(seats, it.takeLast(3))) }
            .map { it.first * 8 + it.second }
    }

    private fun findPosition(list: List<Int>, binary: String): Int {
        var seats = list

        binary.forEach { seats = takeHalf(it, seats) }

        return seats.first()
    }

    private fun takeHalf(direction: Char, input: List<Int>): List<Int> {
        return when (direction) {
            'B', 'R' -> {
                input.takeLast((input.size + 1) / 2)
            }
            else -> input.take((input.size + 1) / 2)
        }
    }

    fun findMissingSeat(seats: List<Int>): Int {
        val sortedSeats = seats.sorted()
        var index = sortedSeats.first()
        sortedSeats.forEach {
            if(it != index) {
                return index
            }
            index++
        }
        return 0
    }
}

fun main() {

    val day5 = Day5()

    val input = day5.getInput()
    val seats = day5.convertInputToSeatID(input)

    // PART ONE
    val result = seats.max()
    println("$result is the highest seat ID")

    // PART TWO
    val result2 = day5.findMissingSeat(seats)
    println("$result2 is the ID of your seat")
}