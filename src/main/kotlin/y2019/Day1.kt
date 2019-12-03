package y2019

import java.io.File
import kotlin.math.floor

/*
Fuel required to launch a given module is based on its mass. Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.

The Fuel Counter-Upper needs to know the total fuel requirement. To find it, individually calculate the fuel needed for the mass of each module (your puzzle input), then add together all the fuel values.

What is the sum of the fuel requirements for all of the modules on your spacecraft?
 */

class Day1 {

    fun getInput(): List<Double> {
        val input = mutableListOf<Double>()
        File("input/y2019/day1.txt").forEachLine { input.add(it.toDouble()) }
        return input
    }

    fun calculateNeededFuel(mass: Double): Double {
        return floor(mass / 3) - 2
    }

    fun calculateFuelSum(masses: List<Double>): Int {
        return masses.map { calculateNeededFuel(it) }.sum().toInt()
    }

    fun calculateFuelsFuel(mass: Double):Int {
        var fuelNeeded = calculateNeededFuel(mass)
        var additionalFuelNeeded = calculateNeededFuel(fuelNeeded)

        while(additionalFuelNeeded > 0) {
            fuelNeeded += additionalFuelNeeded
            additionalFuelNeeded = calculateNeededFuel(additionalFuelNeeded)
        }

        return fuelNeeded.toInt()
    }

    fun calculateFuelsFuelSum(masses: List<Double>): Int {
        return masses.map { calculateFuelsFuel(it) }.sum()
    }
}

fun main() {

    val day1 = Day1()

    val input = day1.getInput()

    // PART ONE
    val result = day1.calculateFuelSum(input)
    println("The sum of the fuel requirements is $result")

    // PART TWO
    val result2 = day1.calculateFuelsFuelSum(input)
    println("The sum of the fuels fuel requirement is $result2")
}
