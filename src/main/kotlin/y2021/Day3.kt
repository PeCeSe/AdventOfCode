package y2021

import java.io.File

class Day3 {

    fun getInput(): List<String> {
        val input = mutableListOf<String>()
        File("input/y2021/day3.txt").forEachLine { input.add(it) }
        return input
    }

    fun findNumberOfOccurrencesForEachPosition(input: List<String>): List<Int> {
        val result = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        input.forEach {
            it.toList().forEachIndexed { i, byte ->
                if (byte == '1') {
                    result[i]++
                }

            }
        }
        return result
    }

    fun getLifeSupportValue(input: List<String>, isO2: Boolean): String {
        var values = input

        var i = 0

        while (i < 12 && values.size > 1) {
            val occurrences = findNumberOfOccurrencesForEachPosition(values)

            val currentMostOccurring = getSelectedByte(occurrences[i], values.size / 2, isO2)

            values = values.filter { it.substring(i).take(1).contains(currentMostOccurring) }
            i++
        }
        return values.first()
    }

    private fun getSelectedByte(occurrences: Int, halfTotal: Int, isO2: Boolean): Char {
        var mostUsedBit = if (occurrences >= halfTotal) '1' else '0'

        if (!isO2) {
            mostUsedBit = invertByteString(mostUsedBit.toString()).first()
        }
        return mostUsedBit
    }

    fun findGammaRate(input: List<Int>, sum: Int): String {
        var resultString = ""
        input.forEach { num ->
            if (num >= sum / 2) {
                resultString += '1'
            } else {
                resultString += '0'
            }
        }

        return resultString
    }

    fun invertByteString(byteString: String): String {
        return byteString.map {
            when (it) {
                '0' -> '1'
                '1' -> '0'
                else -> '2'
            }
        }.joinToString("")
    }

    fun multiplyByteStringsToInt(a: String, b: String): Int {
        return a.toInt(2) * b.toInt(2)
    }

}

fun main() {

    val day3 = Day3()

    val input = day3.getInput()

    // PART ONE
    val numberOfOccurrences = day3.findNumberOfOccurrencesForEachPosition(input)
    val gammaRate = day3.findGammaRate(numberOfOccurrences, input.size)
    val epsilonRate = day3.invertByteString(gammaRate)
    val powerConsumption = day3.multiplyByteStringsToInt(gammaRate, epsilonRate)
    println("Gamma Rate: $gammaRate, ${gammaRate.toInt(2)}")
    println("EpsilonRate: $epsilonRate, ${epsilonRate.toInt(2)}")
    println("Power Consumption: $powerConsumption")

    println("----------------------------------")

    // PART TWO
    val o2Rating = day3.getLifeSupportValue(input, true)
    val co2Rating = day3.getLifeSupportValue(input, false)
    val lifeSupport = day3.multiplyByteStringsToInt(o2Rating, co2Rating)

    println("0xygen Generator Rating: $o2Rating, ${o2Rating.toInt(2)}")
    println("CO2 Scrubber Rating: $co2Rating, ${co2Rating.toInt(2)}")
    println("Life support: $lifeSupport")
}