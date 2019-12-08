package y2019

import java.io.File
import java.lang.Exception

/*
After providing 1 to the only input instruction and passing all the tests, what diagnostic code does the program produce?
 */

class Day5 {


    fun getInput(): MutableList<Int> {
        return File("input/y2019/day5.txt")
            .readLines()
            .joinToString()
            .split(',')
            .map { it.toInt() }
            .toMutableList()
    }

    fun processIntcode(input: Int, intCode: MutableList<Int>): List<Int> {
        var index = 0
        var resultIntCode = intCode

        val mode = 0

        while(index < intCode.size) {

            resultIntCode = when (resultIntCode[index]) {
                1 -> {
                    val result = handleAddition(resultIntCode, index, mode)
                    result
                }
                2 -> {
                    val result = handleMultiplication(resultIntCode, index, mode)
                    index += 4
                    result
                }
                3 -> {
                    val result = handleAssignment(resultIntCode, index, input, mode)
                    index += 2
                    result
                }
                4 -> {
                    handleOutput(resultIntCode, index, mode)
                    index += 2
                    resultIntCode
                }
                99 -> return resultIntCode
                else -> throw Exception("Invalid code ${resultIntCode[index]}")
            }
        }
        return resultIntCode
    }

    private fun handleAssignment(resultIntCode: MutableList<Int>, index: Int, input: Int, mode: Int): MutableList<Int> {
        resultIntCode[resultIntCode[index + 1]] = input
        return resultIntCode
    }

    private fun handleOutput(intcode: MutableList<Int>, index: Int, mode: Int) {
        println(getWithMode(intcode, index + 1, mode))
    }

    private fun handleAddition(intcode: MutableList<Int>, index: Int, mode: Int): MutableList<Int> {
        val a = getWithMode(intcode, index + 1, mode)
        val b = getWithMode(intcode, index + 2, mode)

        intcode[intcode[index + 3]] = a + b

        return intcode
    }

    private fun handleMultiplication(intcode: MutableList<Int>, index: Int, mode: Int): MutableList<Int> {
        val a = getWithMode(intcode, index + 1, mode)
        val b = getWithMode(intcode, index + 2, mode)

        intcode[intcode[index + 3]] = a * b

        return intcode
    }

    private fun getWithMode(intcode: MutableList<Int>, index: Int, mode: Int): Int{
        if(mode == 0) {
            return intcode[intcode[index]]
        } else if (mode == 1) {
            return intcode[index]
        }

        throw Exception("$mode is not a valid mode")
    }

    private fun setWithMode(intcode: MutableList<Int>, index: Int, mode: Int): Int{
        if(mode == 0) {
            return intcode[intcode[index]]
        } else if (mode == 1) {
            return intcode[index]
        }

        throw Exception("$mode is not a valid mode")
    }
}

fun main() {

    val day5 = Day5()

    val input = day5.getInput()

    val result = day5.processIntcode(1, input)
}
