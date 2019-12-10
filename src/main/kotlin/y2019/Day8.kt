package y2019

import java.io.File

/*
The image you received is 25 pixels wide and 6 pixels tall.

To make sure the image wasn't corrupted during transmission,
the Elves would like you to find the layer that contains the fewest 0 digits.
On that layer, what is the number of 1 digits multiplied by the number of 2 digits?

0 is black, 1 is white, and 2 is transparent.
What message is produced after decoding your image?
 */

class Day8 {


    fun getInput(): String {
        return File("input/y2019/day8.txt")
            .readLines()
            .joinToString()
    }

    fun checkDataForCorruption(input: String, width: Int, height: Int): Int {
        val layers = input.chunked(width * height)
        val layerWithFewestZeroes = layers.minBy { l -> l.count { it == '0' } }!!
        return layerWithFewestZeroes.count{it == '1'} * layerWithFewestZeroes.count { it == '2' }
    }

    fun decodeImage(input: String, width: Int, height: Int): List<String> {
        val layers = input.chunked(width * height).map { it.chunked(width) }.reversed()
        val finalImage = layers.first().toMutableList()

        layers.map { layer ->
            layer.mapIndexed { i, h ->
                h.mapIndexed { j, w ->
                    if(w != '2') {
                        val currentPixel = finalImage[i]
                        finalImage[i] = currentPixel.replaceAtIndex(j, w)
                    }
                }
            }
        }

        return finalImage
    }

    fun printImage(image: List<String>) {
        image.map { h ->
            h.map { w -> print(getColor(w)) }
            println()
        }
    }

    private fun getColor(char: Char): Char {
        return when(char) {
            '0' -> ' '
            '1' -> '▉'
            '2' -> '▢'
            else -> '?'
        }
    }

    private fun String.replaceAtIndex(index: Int, value: Char): String {
        val charList = this.toCharArray()
        charList[index] = value
        return String(charList)
    }

}

fun main() {

    val day8 = Day8()

    val input = day8.getInput()

    val result = day8.checkDataForCorruption(input, 25, 6)
    println("The result is $result")

    val finalImage = day8.decodeImage(input, 25, 6)

    day8.printImage(finalImage)
}
