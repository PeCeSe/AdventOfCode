package y2021
import java.io.File

class Day1 {

    fun getInput(): List<Int> {
        val input = mutableListOf<Int>()
        File("input/y2021/day1.txt").forEachLine { input.add(it.toInt()) }
        return input
    }

    fun findNumberOfIncreases(input: List<Int>): Int {
        var num = 0
        input.forEachIndexed { i, n ->
            if(i < input.size - 1) {
                if(n < input[i+1]) {
                    num++
                }
            }
        }
        return num
    }

    fun sumThreeMeasurementSlidingWindows(input: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        input.forEachIndexed{i, n ->
            if(i > 0 && i < input.size - 1) {
                result.add(input[i-1] + n + input[i+1])
            }
        }
        return result
    }

}

fun main() {

    val day1 = Day1()

    val input = day1.getInput()

    // PART ONE
    println( day1.findNumberOfIncreases(input) )

    // PART TWO
    val tmsw = day1.sumThreeMeasurementSlidingWindows(input)
    println(day1.findNumberOfIncreases(tmsw))

}