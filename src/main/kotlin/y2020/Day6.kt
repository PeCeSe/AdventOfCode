package y2020

import java.io.File

class Day6 {

    fun getInput(): List<List<String>> {
        val input = mutableListOf<MutableList<String>>()
        var index = 0
        input.add(mutableListOf())
        File("input/y2020/day6.txt").forEachLine {
            if (it.isNotEmpty()) {
                input[index].add(it)
            } else {
                index++
                input.add(mutableListOf())
            }
        }
        return input
    }

    fun countAnswersPerGroup(input: List<List<String>>): List<Int> {
        return input.map{ it.joinToString("").toList().distinct() }.map { it.size }
    }

    fun countAnswersPerGroupWhereEveryoneSaidYes(input: List<List<String>>): List<Int> {
        return input.map { group -> group.size to group.joinToString("").toList().groupBy { answer -> answer } }
            .map { group -> group.second.filter{ answer -> answer.value.size == group.first }}
            .map { it.size }
    }
}

fun main() {

    val day6 = Day6()

    val input = day6.getInput()

    // PART ONE
    val result = day6.countAnswersPerGroup(input)
    println("The sum of the counts is ${result.sum()}")

    // PART TWO
    val result2 = day6.countAnswersPerGroupWhereEveryoneSaidYes(input)
    println("The sum of the counts where everyone in a groups said yes is ${result2.sum()}")
}