package y2019

import java.io.File
import java.lang.Exception

/*
Once you have a working computer, the first step is to restore the gravity assist program (your puzzle input)
to the "1202 program alarm" state it had just before the last computer caught fire. To do this,
before running the program, replace position 1 with the value 12 and replace position 2 with the value 2.
What value is left at position 0 after the program halts?
 */

class Day2 {

    fun getInput(): MutableList<Int> {
        return File("input/y2019/day2.txt")
            .readLines()
            .joinToString()
            .split(',')
            .map { it.toInt() }
            .toMutableList()
    }

    fun processIntcode(intCode: MutableList<Int>,  noun: Int? = null, verb: Int? = null): List<Int> {
        var index = 0
        var resultIntCode = intCode

        resultIntCode = setNounAndVerb(resultIntCode, noun, verb)

        while(index < intCode.size) {
            resultIntCode = when (resultIntCode[index]) {
                1 -> handleAddition(resultIntCode, index)
                2 -> handleMultiplication(resultIntCode, index)
                99 -> return resultIntCode
                else -> throw Exception("Invalid code ${resultIntCode[index]}")
            }
            index += 4
        }
        return resultIntCode
    }

    private fun setNounAndVerb(resultcode: MutableList<Int>, noun: Int?, verb: Int?): MutableList<Int> {
        if(noun != null){
            resultcode[1] = noun
        }
        if(verb != null) {
            resultcode[2] = verb
        }
        return resultcode
    }

    private fun handleAddition(intcode: MutableList<Int>, index: Int): MutableList<Int> {
        val a = intcode[intcode[index + 1]]
        val b = intcode[intcode[index + 2]]

        intcode[intcode[index + 3]] = a + b

        return intcode
    }

    private fun handleMultiplication(intcode: MutableList<Int>, index: Int): MutableList<Int> {
        val a = intcode[intcode[index + 1]]
        val b = intcode[intcode[index + 2]]

        intcode[intcode[index + 3]] = a * b

        return intcode
    }

    fun bruteforce(intcode: MutableList<Int>){

        var a = 0
        var b = 0

        while(a < 99) {
            while(b < 99) {
                try {
                    val result = processIntcode(intcode.toMutableList(), a, b)
                    if(result[0] == 19690720) {
                        println("Noun $a and verb $b gives value 19690720")
                        return
                    }
                } catch (e: Exception) {
                    println("$e for noun $a and verb $b")
                }

                b++
            }
            b = 0
            a++
        }
    }


}

fun main() {

    val day2 = Day2()

    val input = day2.getInput()

    /*val result = day2.processIntcode(input, 12, 2)
    println(result)*/

    day2.bruteforce(input)
}
