package `2023`

import `2023`.day04.getScratchCard
import println

fun main() {

    fun part1(input: List<String>): Int {
        var scratchCards = input.map { getScratchCard(it) }
        var scores = scratchCards.map { it.points }
        println(scores)
        return scores.sum()
    }

    fun part2(input: List<String>): Any {
        val scratchCards = input.map { getScratchCard(it) }

        val copies = mutableMapOf<Int, Int>()

        for (card in scratchCards) {

            var size = card.winners.size
            var bottom = card.cardNumber + 1
            var top = card.cardNumber + size

            var copiesToAdd = (copies[card.cardNumber] ?: 0) + 1

            for (i in bottom..top) {
                val a = copies[i]

                if (a != null) {
                    copies[i] = a + copiesToAdd
                } else {
                    copies[i] = copiesToAdd
                }
            }
        }

        return copies.map { it.value }.sum() + scratchCards.size
    }

    val testInput = Input.asList("2023/Day04_test")
    part1(testInput).println()
    part2(testInput).println()

    val input = Input.asList("2023/Day04")
    part1(input).println()
    part2(input).println()
}