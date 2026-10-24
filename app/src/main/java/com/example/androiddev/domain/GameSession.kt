
package com.example.androiddev.domain

enum class GameStatus {
    IN_PROGRESS,
    WON,
    LOST
}

data class GameSession(
    val difficulty: GameDifficulty,
    val secretCode: List<Int>,
    val attempts: List<GuessResult> = emptyList()
) {

    init {
        require(secretCode.size == difficulty.codeLength)
        require(secretCode.all { it in 0..9 })
        require(secretCode.distinct().size == secretCode.size)
        require(attempts.size <= difficulty.maxAttempts)
    }

    val remainingAttempts: Int
        get() = difficulty.maxAttempts - attempts.size

    val status: GameStatus
        get() = when {
            attempts.lastOrNull()?.isWin == true ->
                GameStatus.WON

            remainingAttempts == 0 ->
                GameStatus.LOST

            else ->
                GameStatus.IN_PROGRESS
        }

    fun submitGuess(guess: List<Int>): GameSession {
        check(status == GameStatus.IN_PROGRESS) {
            "Игра уже завершена"
        }

        val result = BullsAndCows.checkGuess(
            secretCode = secretCode,
            guess = guess
        )

        return copy(
            attempts = attempts + result
        )
    }

    companion object {

        fun start(difficulty: GameDifficulty): GameSession {
            return GameSession(
                difficulty = difficulty,
                secretCode = BullsAndCows.generateSecretCode(difficulty)
            )
        }
    }
}
