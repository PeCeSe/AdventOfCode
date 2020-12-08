package y2020

import java.io.File

class Day8 {

    fun getInput(): List<Pair<String, Int>> {
        val input = mutableListOf<Pair<String, Int>>()
        File("input/y2020/day8.txt").forEachLine { input.add(it.take(3) to it.split(" ").last().toInt()) }
        return input
    }

    fun findAccumulatorValue(input: List<Pair<String, Int>>): Pair<Int, Boolean> {
        var accumulator = 0
        val visitedIndexes = mutableListOf<Int>()
        var index = 0

        while (!visitedIndexes.contains(index) && index < input.size) {
            visitedIndexes.add(index)
            when (input[index].first) {
                "acc" -> {
                    accumulator += input[index].second
                    index++
                }
                "jmp" -> {
                    index += input[index].second
                }
                else -> {
                    index++
                }
            }
        }
        return accumulator to (index >= input.size)
    }

    fun findAccumulatedValueWithoutCorruption(input: List<Pair<String, Int>>): Int {
        for (i in input.indices) {
            val fixedInput = input.toMutableList()
            when (input[i].first) {
                "jmp" -> {
                    fixedInput[i] = "nop" to fixedInput[i].second
                }
                "nop" -> {
                    fixedInput[i] = "jmp" to fixedInput[i].second
                }
            }
            val result = findAccumulatorValue(fixedInput)
            if(result.second ) {
                return result.first
            }
        }
        return -1
    }

}

fun main() {

    val day8 = Day8()

    val input = day8.getInput()

    // PART ONE
    val result = day8.findAccumulatorValue(input)
    println("${result.first} is the accumulated value")

    // PART TWO
    val result2 = day8.findAccumulatedValueWithoutCorruption(input)
    println("$result2 is the accumulated value when the input is fixed")
}