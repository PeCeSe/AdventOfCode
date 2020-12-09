package y2020

import java.io.File

class Day9 {

    fun getInput(): List<Long> {
        val input = mutableListOf<Long>()
        File("input/y2020/day9.txt").forEachLine { input.add( it.toLong()) }
        return input
    }

    fun findFirstInvalidNumber(data: List<Long>, preambleSize: Int): Long {
        var index = preambleSize

        while(index < data.size){
            val preamble = data.subList(index - preambleSize, index)
            if(data[index] !in  sumPreamble(preamble)){
                return data[index]
            }
            index++
        }
        return -1
    }

    private fun sumPreamble(preamble: List<Long>): List<Long> {
        return preamble.map { first ->
            preamble.filter{ it != first }.map { second ->
                first + second
            }
        }.flatMap { it.toList() }.distinct()
    }

    fun findEncryptionWeakness(invalidNum: Long, data: List<Long>): Long {
        val encryptionWeakness = findNumbersThatSumToInvalidNumber(invalidNum, data)
        return encryptionWeakness.min()?.plus(encryptionWeakness.max()!!)!!
    }

    private fun findNumbersThatSumToInvalidNumber(invalidNum: Long, data: List<Long>): List<Long> {
        val relevantData = data.filter { it < invalidNum }

        for( i in relevantData.indices){
            val sumNums = mutableListOf<Long>()

            var j = i

            while(sumNums.sum() < invalidNum) {
                sumNums.add(relevantData[j])
                j++
            }

            if(sumNums.sum() == invalidNum) {
                return sumNums
            }
        }

        return emptyList()
    }
}

fun main() {

    val day9 = Day9()

    val input = day9.getInput()

    // PART ONE
    val result = day9.findFirstInvalidNumber(input, 25)
    println("${result} is the first invalid number")

    // PART TWO
    val result2 = day9.findEncryptionWeakness(result, input)
    println("The encryption weakness is $result2")
}