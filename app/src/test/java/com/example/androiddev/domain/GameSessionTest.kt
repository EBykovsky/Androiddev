
package com.example.androiddev.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameSessionTest {

    @Test
    fun newGameStartsWithoutAttempts() {
        val session = GameSession.start(GameDifficulty.NORMAL)

        assertEquals(0, session.attempts.size)
        assertEquals(5, session.remainingAttempts)
        assertEquals(GameStatus.IN_PROGRESS, session.status)
        assertEquals(4, session.secretCode.size)
    }

    @Test
    fun attemptIsAddedToHistory() {
        val session = GameSession(
            difficulty = GameDifficulty.NORMAL,
            secretCode = listOf(4, 2, 7, 1)
        )

        val updated = session.submitGuess(
            listOf(4, 1, 3, 2)
        )

        assertEquals(0, session.attempts.size)
        assertEquals(1, updated.attempts.size)
        assertEquals(4, updated.remainingAttempts)
        assertEquals(1, updated.attempts[0].bulls)
        assertEquals(2, updated.attempts[0].cows)
    }

    @Test
    fun gameEndsAfterCorrectGuess() {
        val session = GameSession(
            difficulty = GameDifficulty.NORMAL,
            secretCode = listOf(4, 2, 7, 1)
        )

        val updated = session.submitGuess(
            listOf(4, 2, 7, 1)
        )

        assertEquals(GameStatus.WON, updated.status)
        assertTrue(updated.attempts.last().isWin)
    }

    @Test
    fun gameEndsWhenAttemptsRunOut() {
        var session = GameSession(
            difficulty = GameDifficulty.NORMAL,
            secretCode = listOf(4, 2, 7, 1)
        )

        repeat(5) {
            session = session.submitGuess(
                listOf(1, 7, 2, 4)
            )
        }

        assertEquals(0, session.remainingAttempts)
        assertEquals(5, session.attempts.size)
        assertEquals(GameStatus.LOST, session.status)
    }

    @Test(expected = IllegalStateException::class)
    fun cannotContinueAfterVictory() {
        val session = GameSession(
            difficulty = GameDifficulty.NORMAL,
            secretCode = listOf(4, 2, 7, 1)
        )

        val finished = session.submitGuess(
            listOf(4, 2, 7, 1)
        )

        finished.submitGuess(
            listOf(1, 2, 3, 4)
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidGuessIsRejected() {
        val session = GameSession(
            difficulty = GameDifficulty.NORMAL,
            secretCode = listOf(4, 2, 7, 1)
        )

        session.submitGuess(
            listOf(1, 1, 2, 3)
        )
    }
}
