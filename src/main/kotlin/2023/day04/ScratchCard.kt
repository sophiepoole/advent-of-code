package `2023`.day04

import kotlin.math.pow

fun String.getListOfNumbers(): List<Int> {
    return split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() }
}

fun getScratchCard(input: String): ScratchCard {
    val (card, numbers) = input.split(":")
    val cardNumberSplit = card.split(" ")
    val (winning, yours) = numbers.split("|")

    return ScratchCard(
        cardNumberSplit.last().toInt(),
        winning.getListOfNumbers(),
        yours.getListOfNumbers()
    )
}

class ScratchCard(cN: Int, wN: List<Int>, yourn: List<Int>) {
    val cardNumber: Int = cN
    private val winningNumbers: List<Int> = wN
    private val yourNumbers: List<Int> = yourn
    val winners: List<Int> = yourNumbers.filter { winningNumbers.contains(it) }
    val points: Int

    init {
        points = calculatePoints()
    }

    private fun yourWinningNumbers(): List<Int> {
        return yourNumbers.filter { winningNumbers.contains(it) }
    }

    private fun calculatePoints(): Int {
        if (winners.isEmpty()) {
            return 0
        }
        return 2.0.pow(yourWinningNumbers().size - 1).toInt()
    }
}
