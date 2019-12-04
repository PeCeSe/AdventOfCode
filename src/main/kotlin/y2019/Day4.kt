package y2019

/*
It is a six-digit number.
The value is within the range given in your puzzle input.
Two adjacent digits are the same (like 22 in 122345).
Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).

How many different passwords within the range given in your puzzle input meet these criteria?
 */

class Day4 {

    fun getInput(): IntRange {
        return 145852..616942
    }

    fun findValidPasswordsWithinRangePart1(range: IntRange): Int{
        return range.filter { isValidPasswordPart1(it) }.count()
    }

    fun isValidPasswordPart1(password: Int): Boolean {
        return password.containsDoubleDigits() && password.isOnlyIncreasingDigits()
    }

    fun findValidPasswordsWithinRangePart2(range: IntRange): Int{
        return range.filter { isValidPasswordPart2(it) }.count()
    }

    fun isValidPasswordPart2(password: Int): Boolean {
        return password.isOnlyIncreasingDigits() && password.containsExactlyTwoRepeatingDigits()
    }

    private fun Int.containsDoubleDigits(): Boolean {
        val numbers = this.toString().toCharArray().map { it.toInt() }

        var i = 1
        for(n in numbers){
            if(i < numbers.size && numbers[i] == n){
                return true
            }
            i++
        }

        return false
    }

    private fun Int.isOnlyIncreasingDigits(): Boolean {
        val numbers = this.toString().toCharArray().map { it.toInt() }

        var i = 1
        for(n in numbers){
            if(i < numbers.size  && numbers[i] < n){
                return false
            }
            i++
        }

        return true
    }

    private fun Int.containsExactlyTwoRepeatingDigits(): Boolean {
        val numbers = this.toString().toCharArray().map { it.toInt() }

        val something = numbers.groupBy { it }.filter { it.value.size == 2 }.count() > 0

        return something
    }
}

fun main() {

    val day4 = Day4()

    val input = day4.getInput()

    val result = day4.findValidPasswordsWithinRangePart1(input)
    println("The number of valid passwords is: $result")

    val result2 = day4.findValidPasswordsWithinRangePart2(input)
    println("The number of valid passwords with new  criteria is: $result2")
}
