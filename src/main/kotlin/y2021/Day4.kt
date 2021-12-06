package y2021

import java.io.File

class Day4 {

    private val bingoNumbers = listOf(23,30,70,61,79,49,19,37,64,48,72,34,69,53,15,74,89,38,46,36,28,32,45,2,39,58,11,62,97,40,14,87,96,94,91,92,80,99,6,31,57,98,65,10,33,63,42,17,47,66,26,22,73,27,7,0,55,8,56,29,86,25,4,12,51,60,35,50,5,75,95,44,16,93,21,3,24,52,77,76,43,41,9,84,67,71,83,88,59,68,85,82,1,18,13,78,20,90,81,54)

    fun getInput(): List<BingoBoard> {
        val input = mutableListOf<String>()
        File("input/y2021/day4.txt").forEachLine { input.add(it) }

        return input
            .filter { it.isNotEmpty() }
            .chunked(5)
            .map { chunk ->
                chunk.map { text -> text.split(" ") }
                    .map { stringList ->
                        stringList.map { stringNumber ->
                            stringNumber.trim().toInt()
                        }
                    }
            }.map { chunk ->
            BingoBoard(chunk, getColumns(chunk))
        }
    }

    private fun getColumns(rows: List<List<Int>>): List<List<Int>> {
        val columns = mutableListOf<List<Int>>()
        var i = 0

        while (i < rows.first().size) {
            val column = rows.map { it.filterIndexed { index, _ -> index == i } }.flatten()
            columns.add(column)
            i++
        }

        return columns
    }

    fun bingoAllBoards(boards: List<BingoBoard>): List<BingoBoard> {
        return boards.map { findWinningIndex(it) }.map { calculateScore(it) }
    }

    private fun findWinningIndex(board: BingoBoard): BingoBoard {
        bingoNumbers.forEachIndexed { index, _ ->
            val currentNumbersDrawn = bingoNumbers.subList(0, index + 1)
            board.rows.plus(board.columns).forEach { list ->
                if (currentNumbersDrawn.containsAll(list)) {
                    board.bingoAtIndex = index
                    return board
                }
            }
        }
        return board
    }

    private fun calculateScore(board: BingoBoard): BingoBoard {
        val currentNumbersDrawn = bingoNumbers.subList(0, board.bingoAtIndex + 1)
        val winningNumber = bingoNumbers[board.bingoAtIndex]
        val sumOfUnmarkedNumbers = board.rows.flatten().filter { !currentNumbersDrawn.contains(it) }.sum()
        board.boardScore = sumOfUnmarkedNumbers * winningNumber
        return board
    }

}

data class BingoBoard(val rows: List<List<Int>>, val columns: List<List<Int>>, var bingoAtIndex: Int = -1, var boardScore: Int = -1)

fun main() {

    val day4 = Day4()

    val input = day4.getInput()

    // PART ONE
    val boards = day4.bingoAllBoards(input)
    val winningBoard = boards.minByOrNull { it.bingoAtIndex }!!
    println("The score of the winning board is: ${winningBoard.boardScore}, winning after just ${winningBoard.bingoAtIndex + 1} numbers drawn!")

    // PART TWO
    val losingBoard = boards.maxByOrNull { it.bingoAtIndex }!!
    println("The score of the losing board is: ${losingBoard.boardScore}, losing after a whopping ${losingBoard.bingoAtIndex + 1} numbers drawn!")

}