
package com.example.androiddev.domain

enum class GameDifficulty(
    val codeLength: Int,
    val maxAttempts: Int
) {
    NORMAL(4, 5),
    HARD(6, 8)
}

enum class DigitResult {
    BULL,
    COW,
    MISS
}

data class GuessResult(
    val guess: List<Int>,
    val digitResults: List<DigitResult>
) {
    val bulls: Int
        get() = digitResults.count { it == DigitResult.BULL }

    val cows: Int
        get() = digitResults.count { it == DigitResult.COW }

    val isWin: Boolean
        get() = digitResults.isNotEmpty() &&
                bulls == digitResults.size
}

object BullsAndCows {

    fun generateSecretCode(
        difficulty: GameDifficulty
    ): List<Int> {
        return (0..9)
            .shuffled()
            .take(difficulty.codeLength)
    }

    fun checkGuess(
        secretCode: List<Int>,
        guess: List<Int>
    ): GuessResult {

        require(secretCode.size == guess.size)
        require(secretCode.isNotEmpty())
        require(secretCode.distinct().size == secretCode.size)
        require(guess.distinct().size == guess.size)
        require(secretCode.all { it in 0..9 })
        require(guess.all { it in 0..9 })

        val results = guess.mapIndexed { index, digit ->
            when {
                digit == secretCode[index] -> DigitResult.BULL
                digit in secretCode -> DigitResult.COW
                else -> DigitResult.MISS
            }
        }

        return GuessResult(
            guess = guess,
            digitResults = results
        )
    }
}
