package y2020

import java.io.File

class Day2 {

    fun getInput(): List<String> {
        val input = mutableListOf<String>()
        File("input/y2020/day2.txt").forEachLine { input.add(it) }
        return input
    }

    fun convertStringsToRangePasswordData(input: List<String>): List<Triple<IntRange, Char, String>> {
        return convertStringsToPositionPasswordData(input).map { Triple(getIntRange(it.first), it.second, it.third) }
    }

    fun convertStringsToPositionPasswordData(input: List<String>): List<Triple<Pair<Int, Int>, Char, String>> {
        return input.map { line ->
            val passwordInfo = line.split(" ")
            Triple(splitToPair(passwordInfo[0]), passwordInfo[1].first(), passwordInfo[2])
        }
    }

    private fun splitToPair(inputString: String): Pair<Int, Int> {
        return inputString.split("-").map { it.toInt() }.zipWithNext().first()
    }

    private fun getIntRange(values: Pair<Int, Int>): IntRange {
        return IntRange(values.first, values.second)
    }

    fun countValidRangePasswords(passwords: List<Triple<IntRange, Char, String>>): Int {
        return passwords.map { isRangePasswordValid(it) }.count { it }
    }

    private fun isRangePasswordValid(password: Triple<IntRange, Char, String>): Boolean {
        return password.third.count { it == password.second } in password.first
    }

    fun countValidPositionPasswords(passwords: List<Triple<Pair<Int, Int>, Char, String>>): Int {
        return passwords.map { isPositionPasswordValid(it) }.count { it }
    }

    private fun isPositionPasswordValid(password: Triple<Pair<Int, Int>, Char, String>): Boolean {
        return (password.third[password.first.first - 1 ] == password.second) xor (password.third[password.first.second -1 ] == password.second)
    }
}

fun main() {

    val day2 = Day2()

    val input = day2.getInput()
    val rangePasswords = day2.convertStringsToRangePasswordData(input)
    val positionPasswords = day2.convertStringsToPositionPasswordData(input)

    // PART ONE
    val result = day2.countValidRangePasswords(rangePasswords)
    println("There are $result valid passwords")

    // PART TWO
    val result2 = day2.countValidPositionPasswords(positionPasswords)
    println("There are $result2 valid passwords")
}