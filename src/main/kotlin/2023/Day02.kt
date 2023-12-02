package `2023`

import println

data class Turn(
    val red: Int,
    val blue: Int,
    val green: Int
)

fun main() {
    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    val green = "(\\d*) green".toRegex()
    val blue = "(\\d*) blue".toRegex()
    val red = "(\\d*) red".toRegex()

    fun getTurns(input: String): Turn {
        var numReds = red.find(input)?.groupValues?.last()?.toInt() ?: 0
        var numBlues = blue.find(input)?.groupValues?.last()?.toInt() ?: 0
        var numGreens = green.find(input)?.groupValues?.last()?.toInt() ?: 0

        return Turn(numReds, numBlues, numGreens)
    }

    fun checkTurns(input: String): Boolean {
        var numReds = red.find(input)?.groupValues?.last()?.toInt() ?: 0
        var numBlues = blue.find(input)?.groupValues?.last()?.toInt() ?: 0
        var numGreens = green.find(input)?.groupValues?.last()?.toInt() ?: 0

        if (numGreens > maxGreen || numReds > maxRed || numBlues > maxBlue) {
            return false
        }

        return true
    }

    fun checkCubes(rawGame: String): Int {
        var split = rawGame.split(":")
        var gameId = split.first().removePrefix("Game ")
        var turns = split.last().split(";")

        var booleans = turns.map { checkTurns(it) }

        if (booleans.contains(false)) {
            return 0
        }

        return gameId.toInt()
    }

    fun getPower(rawGame: String): Int {
        var split = rawGame.split(":")
        var gameId = split.first().removePrefix("Game ")
        var turns = split.last().split(";")

        var list = turns.map { getTurns(it) }
        var r = list.maxOf { it.red }
        var b = list.maxOf { it.blue }
        var g = list.maxOf { it.green }

        return r*b*g

    }

    fun part1(input: List<String>): Int {
        return input.sumOf { checkCubes(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { getPower(it) }
    }

    val testInput = Input.asList("2023/Day02_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = Input.asList("2023/Day02")
    part1(input).println()
    part2(input).println()
}