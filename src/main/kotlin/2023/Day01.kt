package `2023`

import println

fun main() {

    fun getCalibrationValue(calibration: String): Int {
        val digits = calibration.filter { it.isDigit() }
        val first = digits.first()
        val last = digits.last()
        return "$first$last".toInt()
    }

    fun getCalibrationValueWords(calibration: String): Int {

        val numberMap = mapOf(
            "one" to "1", "two" to "2", "three" to "3", "four" to "4",
            "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9"
        )

        val validDigits = numberMap.keys + numberMap.values

        val first = calibration.findAnyOf(validDigits)!!.second
        val last = calibration.findLastAnyOf(validDigits)!!.second

        return "${numberMap.getOrDefault(first, first)}${numberMap.getOrDefault(last, last)}".toInt()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { getCalibrationValue(it) }
    }

    fun part2(input: List<String>): Any {
        return input.sumOf { getCalibrationValueWords(it) }
    }

    val testInput = Input.asList("2023/Day01_test")
    part1(testInput).println()
    val testInput2 = Input.asList("2023/Day01_test_part2")
    part2(testInput2).println()

    val input = Input.asList("2023/Day01")
    part1(input).println()
    part2(input).println()
}