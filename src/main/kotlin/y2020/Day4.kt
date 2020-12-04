package y2020

import java.io.File

class Day4 {

    private val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    fun getInput(): List<String> {
        val input = mutableListOf<String>()
        File("input/y2020/day4.txt").forEachLine { if (it.length > 1) input.add(it) }
        return input
    }

    fun convertInputToPassports(input: List<String>): List<Map<String, String>> {
        val passports = mutableListOf<MutableMap<String, String>>()
        input.mapIndexed { index, passport ->
            passports.add(mutableMapOf())
            passport.split(" ").map { field ->
                val kv = field.split(":")
                passports[index][kv[0]] = kv[1]
            }
        }
        return passports
    }

    private fun filterPassportsWithAllFieldsButCidPresent(passports: List<Map<String, String>>): List<Map<String, String>> {
        return passports.filter { it.size > 6 }
            .filter {
                it.containsKey("byr") &&
                        it.containsKey("iyr") &&
                        it.containsKey("eyr") &&
                        it.containsKey("hgt") &&
                        it.containsKey("hcl") &&
                        it.containsKey("ecl") &&
                        it.containsKey("pid")
            }
    }

    fun countNumberOfPassportsWithAllFieldsButCidPresent(passports: List<Map<String, String>>): Int {
        return filterPassportsWithAllFieldsButCidPresent(passports).count()
    }

    fun countNumberOfValidPassports(passports: List<Map<String, String>>): Int {
        val passportsWithAllFields = filterPassportsWithAllFieldsButCidPresent(passports)

        return filterInvalidValuePassports(passportsWithAllFields).count()
    }

    private fun filterInvalidValuePassports(passports: List<Map<String, String>>): List<Map<String, String>> {
        return passports.filter { it["byr"]?.toInt() in 1920..2002 }
            .filter { it["iyr"]?.toInt() in 2010..2020 }
            .filter { it["eyr"]?.toInt() in 2020..2030 }
            .filter { validateHeight(it["hgt"]) }
            .filter { """^#([a-fA-F0-9]{6})""".toRegex().matches(it["hcl"] ?: error("oups")) }
            .filter { eyeColors.contains(it["ecl"])}
            .filter { it["pid"]?.length == 9}
    }

    private fun validateHeight(height: String?): Boolean {
        if(height == null) {
            return false
        }

        return when (height.takeLast(2)) {
            "cm" -> {
                height.takeWhile { it.isDigit() }.toInt() in 150..193
            }
            "in" -> {
                height.takeWhile { it.isDigit() }.toInt() in 59..76
            }
            else -> {
                false
            }
        }
    }
}

fun main() {

    val day4 = Day4()

    val input = day4.getInput()
    val passports = day4.convertInputToPassports(input)

    // PART ONE
    val result = day4.countNumberOfPassportsWithAllFieldsButCidPresent(passports)
    println("There are $result passports with all fields present")

    // PART TWO
    val result2 = day4.countNumberOfValidPassports(passports)
    println("There are $result2 valid passports")
}