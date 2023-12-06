package `2023`

import println

fun main() {

    fun part1(input: List<String>): Int {
        val time = input[0].substringAfter(": ").split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        val distance = input[1].substringAfter(": ").split(" ").filter { it.isNotBlank() }.map { it.toInt() }


        val counts = List(time.size) { index ->
            val raceLength = time[index]
            (0..raceLength).map { (raceLength - it) * it }.filter { it > distance[index] }
        }.map { it.size }

        return counts.reduce { acc, count -> acc * count }
    }

    fun part2(input: List<String>): Int {
        val time = input[0].substringAfter(": ").replace(" ", "").toLong()
        val distance = input[1].substringAfter(": ").replace(" ", "").toLong()

        return (0..time).map { (time - it) * it }.filter { a -> a > distance }.size
    }

    val testInput = Input.asList("2023/Day06_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = Input.asList("2023/Day06")
    part1(input).println()
    part2(input).println()
}