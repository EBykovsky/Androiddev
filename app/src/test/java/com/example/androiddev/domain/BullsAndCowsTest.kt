
package com.example.androiddev.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BullsAndCowsTest {

    @Test
    fun difficultyParametersAreCorrect() {
        assertEquals(4, GameDifficulty.NORMAL.codeLength)
        assertEquals(5, GameDifficulty.NORMAL.maxAttempts)

        assertEquals(6, GameDifficulty.HARD.codeLength)
        assertEquals(8, GameDifficulty.HARD.maxAttempts)
    }

    @Test
    fun generatedCodeHasUniqueDigits() {
        for (difficulty in GameDifficulty.entries) {
            repeat(100) {
                val code = BullsAndCows.generateSecretCode(difficulty)

                assertEquals(difficulty.codeLength, code.size)
                assertEquals(code.size, code.distinct().size)
                assertTrue(code.all { it in 0..9 })
            }
        }
    }

    @Test
    fun checkGuessIdentifiesBullsCowsAndMisses() {
        val secretCode = listOf(4, 2, 7, 1)
        val guess = listOf(4, 1, 3, 2)

        val result = BullsAndCows.checkGuess(secretCode, guess)

        assertEquals(
            listOf(
                DigitResult.BULL,
                DigitResult.COW,
                DigitResult.MISS,
                DigitResult.COW
            ),
            result.digitResults
        )

        assertEquals(1, result.bulls)
        assertEquals(2, result.cows)
        assertFalse(result.isWin)
    }

    @Test
    fun checkGuessDetectsVictory() {
        val secretCode = listOf(4, 2, 7, 1)
        val guess = listOf(4, 2, 7, 1)

        val result = BullsAndCows.checkGuess(secretCode, guess)

        assertEquals(4, result.bulls)
        assertEquals(0, result.cows)
        assertTrue(result.isWin)
    }

    @Test
    fun zeroCanBeFirstDigit() {
        val secretCode = listOf(0, 2, 7, 1)
        val guess = listOf(0, 2, 7, 1)

        val result = BullsAndCows.checkGuess(secretCode, guess)

        assertTrue(result.isWin)
    }

    @Test(expected = IllegalArgumentException::class)
    fun duplicateDigitsAreRejected() {
        val secretCode = listOf(4, 2, 7, 1)
        val guess = listOf(4, 4, 3, 2)

        BullsAndCows.checkGuess(secretCode, guess)
    }

    @Test(expected = IllegalArgumentException::class)
    fun incorrectCodeLengthIsRejected() {
        val secretCode = listOf(4, 2, 7, 1)
        val guess = listOf(4, 2, 7)

        BullsAndCows.checkGuess(secretCode, guess)
    }
}
