package `2023`.day05

import println

data class GardenRanges(
    val destination: Long,
    val source: Long,
    val range: Long
)

fun getLocationMap(mapText: String): List<GardenRanges> {
    return mapText
        .split("\n")
        .drop(1)
        .map {
            val (destination, source, range) = it.trim().split(" ")
            GardenRanges(destination.toLong(), source.toLong(), range.toLong())
        }
}

fun main() {

    fun part1(input: String): Long {
        val parts = input.split("\n\n")
        val seeds = parts.first()
            .split(":").last().trim()
            .split(" ")
            .map { it.toLong() }

        val locationMaps = parts.drop(1).map { getLocationMap(it) }

        return seeds.minOfOrNull { seed ->
            locationMaps.fold(seed) { sum, it ->
                it.find { sum in it.source..<it.source + it.range }
                    ?.let { sum + it.destination - it.source }
                    ?: sum
            }
        } ?: 0
    }

    fun part2(input: String): Int {
        val parts = input.split("\n\n")
        val seeds = parts.first()
            .split(":").last().trim()
            .split(" ").map { it.toLong() }
            .chunked(2)

        val locationMaps = parts.drop(1).map { getLocationMap(it) }

        var location = 0

        while (true) {
            val seed: Long = locationMaps.reversed()
                .fold(location.toLong()) { sum, it ->
                    it.find { sum in it.destination..<it.destination + it.range }
                        ?.let { sum - (it.destination - it.source) }
                        ?: sum
                }
            val checkSeedInList = seeds.any {
                seed in it.first()..<(it.first() + it[1])
            }

            if (checkSeedInList) {
                break
            }
            location++
        }

        return location
    }

//    val testInput = Input.asText("2023/Day05_test")
//    part1(testInput).println()
//    part2(testInput).println()

    val input = Input.asText("2023/Day05")
    //324724204
    val part1 = part1(input).toInt()
    part1.println()
    check(part1 == 324724204)
    val part2 = part2(input).toInt()
    part2.println()
    check(part2 == 104070862)
}