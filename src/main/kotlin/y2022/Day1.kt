package y2022

import java.io.File

class Day1 {

    fun getInput(): List<List<Int>> {
        val input = mutableListOf<List<Int>>()
        val elf = mutableListOf<Int>()
        File("input/y2022/day1.txt").forEachLine {
            if (it.isNotEmpty()) {
                elf.add(it.toInt())
            } else {
                input.add(elf.toList())
                elf.clear()
            }

        }
        return input
    }

    fun findElfWithMostCalories(elves: List<List<Int>>):Int {
        return elves.map { it.sum() }.max()
    }

    fun findTopThreeElvesWithMostCalories(elves: List<List<Int>>):Int {
        return elves.map { it.sum() }.sorted().takeLast(3).sum()
    }

}

fun main() {

    val day1 = Day1()

    val input = day1.getInput()

    // PART ONE
    println(day1.findElfWithMostCalories(input))

    // PART TWO
    println(day1.findTopThreeElvesWithMostCalories(input))
}