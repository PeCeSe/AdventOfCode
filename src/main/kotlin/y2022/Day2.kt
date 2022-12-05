package y2022

import java.io.File

class Day2 {

    fun getInput(): List<Pair<Char, Char>> {
        val input = mutableListOf<Pair<Char, Char>>()
        File("input/y2022/day2.txt").forEachLine { input.add(it.first() to it.last()) }
        return input
    }

    fun translateInputPart1(input: List<Pair<Char, Char>>): List<Pair<Char, Char>> {
        return input.map { getRPSvalue(it.first) to getRPSvalue(it.second) }
    }

    fun translateInputPart2(input: List<Pair<Char, Char>>): List<Pair<Char, Char>> {
        return input.map { getRPSvalue(it.first) to getRespondingValue(it) }
    }

    private fun getRespondingValue(value: Pair<Char, Char>): Char{
        val oMove = getRPSvalue(value.first)

        return when {
            oMove == 'R' && value.second == 'X' -> 'S'
            oMove == 'R' && value.second == 'Y' -> 'R'
            oMove == 'R' && value.second == 'Z' -> 'P'
            oMove == 'P' && value.second == 'X' -> 'R'
            oMove == 'P' && value.second == 'Y' -> 'P'
            oMove == 'P' && value.second == 'Z' -> 'S'
            oMove == 'S' && value.second == 'X' -> 'P'
            oMove == 'S' && value.second == 'Y' -> 'S'
            else -> 'R'
        }
    }

    private fun getRPSvalue(value: Char): Char{
        return when (value) {
            'A', 'X' -> 'R'
            'B', 'Y' -> 'P'
            'C', 'Z' -> 'S'
            else -> 'N'
        }
    }

    fun findTotalScore(input: List<Pair<Char, Char>>): Int {
        return input.map { findGameResult(it) }.sum()
    }

    fun moveValue(move: Char):Int{
        return when (move){
            'R' -> 1
            'P' -> 2
            'S' -> 3
            else -> 0
        }
    }

    private fun findGameResult(game: Pair<Char, Char>): Int {
        if(game.first == game.second){
            return 3 + moveValue(game.second)
        }

        val winValue = when {
            game.first == 'R' && game.second == 'P' -> 6
            game.first == 'R' && game.second == 'S' -> 0
            game.first == 'P' && game.second == 'R' -> 0
            game.first == 'P' && game.second == 'S' -> 6
            game.first == 'S' && game.second == 'R' -> 6
            else -> 0
        }
        return winValue + moveValue(game.second)
    }

}

fun main() {

    val day2 = Day2()

    val input = day2.getInput()

    // PART ONE
    val translatedInputPart1 = day2.translateInputPart1(input)
    println(day2.findTotalScore(translatedInputPart1))

    // PART TWO
    val translatedInputPart2 = day2.translateInputPart2(input)
    println(day2.findTotalScore(translatedInputPart2))
}