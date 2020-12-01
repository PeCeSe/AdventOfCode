package y2020

import java.io.File

class Day1 {

    fun getInput(): List<Int> {
        val input = mutableListOf<Int>()
        File("input/y2020/day1.txt").forEachLine { input.add(it.toInt()) }
        return input
    }

    fun findTwoNumbersThatAddsToSum(sum: Int, numbers: List<Int>): Pair<Int, Int> {
        return makePairs(numbers).first { it.sum() == sum }
    }

    fun makePairs(numbers: List<Int>): List<Pair<Int, Int>> {
        return numbers.map { first ->
            numbers.filter{ it != first }.map { second ->
                Pair(first, second)
            }
        }.flatMap { it.toList() }.distinct()
    }

    fun findThreeNumbersThatAddsToSum(sum: Int, numbers: List<Int>): Triple<Int, Int, Int> {
        return makeTriplesFaster(numbers, sum).first { it.sum() == sum }
    }

    fun makeTriples(numbers: List<Int>): List<Triple<Int, Int, Int>> {
        return numbers.map { first ->
            numbers.filter { it != first }.map { second ->
                numbers.filter{ it != first || it != second}.map { third ->
                    Triple(first, second, third)
                }
            }.flatMap { it.toList() }
        }.flatMap { it.toList() }.distinct()
    }

    fun makeTriplesFaster(numbers: List<Int>, sum: Int): List<Triple<Int, Int, Int>> {
        return numbers.filter { it <= sum / 3 }
            .mapIndexed{firstIndex, first ->
                numbers.filterIndexed { secondIndex, second -> secondIndex > firstIndex && second <= sum / 2 }
                    .mapIndexed{ secondIndex, second ->
                        numbers.filterIndexed{ thirdIndex, _ -> thirdIndex > secondIndex }.map { third -> Triple(first, second, third) }
                    }.flatMap { it.toList() }
            }.flatMap { it.toList() }

    }

    fun Pair<Int, Int>.sum(): Int {
        return this.first + this.second
    }

    fun Triple<Int, Int, Int>.sum(): Int {
        return this.first + this.second + this.third
    }
}

fun main() {

    val day1 = Day1()

    val sum = 2020

    val input = day1.getInput()

    // PART ONE
    val result = day1.findTwoNumbersThatAddsToSum(sum, input)
    println("The two numbers that adds to $sum are ${result.first} and ${result.second}, and multiplied ${result.first * result.second}")

    // PART TWO
    val result2 = day1.findThreeNumbersThatAddsToSum(sum, input)
    println("The three numbers that adds to $sum are ${result2.first}, ${result2.second} and ${result2.third}. Multiplied they are ${result2.first * result2.second * result2.third}")

}