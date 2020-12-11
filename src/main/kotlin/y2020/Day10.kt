package y2020

import java.io.File

class Day10 {

    fun getInput(): List<Int> {
        val input = mutableListOf<Int>()
        File("input/y2020/day10.txt").forEachLine { input.add( it.toInt()) }
        return input
    }

    fun getProductOf1JDiffAnd3JDiff(input: List<Int>): Int {
        val sortedInput: List<Int> = listOf(0) + input.sorted()
        return sortedInput.mapIndexed{ index, jolt ->
            if((index + 1) < sortedInput.size)
                sortedInput[index + 1] - jolt
            else
                3
        }
            .groupBy { jolt -> jolt }
            .map { it.key to it.value.size }
            .filter { it.first == 1 || it.first == 3 }
            .fold(1, { total, next -> total * next.second })
    }
}

fun main() {

    val day10 = Day10()

    val input = day10.getInput()

    // PART ONE
    val result = day10.getProductOf1JDiffAnd3JDiff(input)
    println("The product of 1-jolts and 3-jolts is ${result}")

    // PART TWO
}