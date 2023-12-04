package `2023`

import println
import kotlin.math.max
import kotlin.math.min

fun Char.isSymbolNotDot(): Boolean {
    return !this.isLetterOrDigit() && this != '.'
}

fun getAdjacent(grid: List<String>, x: Int, y: Int): List<Char> {
    return listOfNotNull(
        grid.getOrNull(x - 1)?.getOrNull(y - 1),
        grid.getOrNull(x - 1)?.getOrNull(y),
        grid.getOrNull(x - 1)?.getOrNull(y + 1),
        grid.getOrNull(x)?.getOrNull(y - 1),
        grid.getOrNull(x)?.getOrNull(y + 1),
        grid.getOrNull(x + 1)?.getOrNull(y - 1),
        grid.getOrNull(x + 1)?.getOrNull(y),
        grid.getOrNull(x + 1)?.getOrNull(y + 1),
    )
}

fun checkMatch(grid: List<String>, y: Int, match: MatchResult): Int? {

    var partNumber = false;

    for (i in match.range) {
        var adjacent = getAdjacent(grid, y, i)
        var seeSymbol = adjacent.map { it.isSymbolNotDot() }.any { it }

//        println("$i: ${adjacent} ${adjacent.map { it.isSymbolNotDot() }}: ${seeSymbol}")
        if (seeSymbol) {
            partNumber = true
            break
        }
    }

    return if (partNumber) match.value.toInt() else null
}

fun processPart1(input: List<String>): List<Int> {
    val regex = Regex("(\\d+)")

    val partNumbers = mutableListOf<Int>()

    for (y in input.indices) {
        val matches = regex.findAll(input[y])

        var map = matches.map { checkMatch(input, y, it) }
            .filterNotNull()

        partNumbers.addAll(map)
    }

    println(partNumbers)

    return partNumbers
}
data class PartNumber(
    val num: Int,
    val y: Int,
    val xRange: IntRange
)

data class Gear(
    val x: Int,
    val y: Int
)

fun isOverlapping(a: IntRange, b: IntRange): Boolean {
    var max = max(a.min(), b.min())
    var min = min(a.max(), b.max())
    return max <= min
}

fun getGearRatio(gear: Gear, partNumbers: List<PartNumber>): Int? {

    val inRange = mutableListOf<Int>()

    println("------------------${gear}-------------")
    for (part in partNumbers) {
        println("Part${part}")
        val gearYRange = gear.y - 1..gear.y + 1
        val gearXRange = gear.x - 1..gear.x + 1

        val xinRange = isOverlapping(part.xRange, gearXRange)
        val yinRange = part.y in gearYRange

        if (xinRange && yinRange) {
            inRange.add(part.num)
        }
    }

    println(inRange)

    if (inRange.size == 2) {
        return inRange[0] * inRange[1]
    }

    return null
}
fun processPart2(input: List<String>): List<Int> {

    val regex = Regex("(\\d+)")
    val partNumbers = mutableListOf<PartNumber>()

    for (y in input.indices) {
        val matches = regex.findAll(input[y])

        var map = matches.map { PartNumber(it.value.toInt(), y, it.range) }

        partNumbers.addAll(map)
    }


    val gearNumbers = mutableListOf<Gear>()
    for (y in input.indices) {
        val gearRegex = Regex("\\*")
        var findAll = gearRegex.findAll(input[y]).map { Gear(it.range.first, y) }
        gearNumbers.addAll(findAll)
    }

    val ratios = mutableListOf<Int>()
    for (gear in gearNumbers) {
        var gearRatio = getGearRatio(gear, partNumbers)
        println(gearRatio)

        if(gearRatio != null) {
            ratios.add(gearRatio)
        }
    }

    return ratios;
}

fun main() {
    fun part1(input: List<String>): Int {
        val partNums = processPart1(input)
        return partNums.sum()
    }

    fun part2(input: List<String>): Int {
        return processPart2(input).sum()
    }

    val testInput = Input.asList("2023/Day03_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = Input.asList("2023/Day03")
    part1(input).println()
    part2(input).println()
}