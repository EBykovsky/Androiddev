
package com.example.androiddev.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddev.R
import com.example.androiddev.domain.GameDifficulty
import com.example.androiddev.domain.GameSession
import com.example.androiddev.domain.GameStatus
import com.example.androiddev.ui.components.GameBoard
import com.example.androiddev.ui.components.GameKeyboard

@Composable
fun GameScreen(
    difficulty: GameDifficulty,
    onHomeClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    var session by remember(difficulty) {
        mutableStateOf(GameSession.start(difficulty))
    }

    var currentGuess by remember(difficulty) {
        mutableStateOf(emptyList<Int>())
    }

    val isPlaying = session.status == GameStatus.IN_PROGRESS

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.game_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 430.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameHeaderButton(
                    text = stringResource(R.string.game_home),
                    onClick = onHomeClick
                )

                Spacer(modifier = Modifier.width(8.dp))

                GameHeaderButton(
                    text = "i",
                    onClick = onInfoClick
                )
            }

            GameBoard(
                difficulty = difficulty,
                attempts = session.attempts,
                currentGuess = currentGuess,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .widthIn(max = 400.dp)
                    .padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            GameKeyboard(
                currentGuess = currentGuess,
                codeLength = difficulty.codeLength,

                onDigitClick = { digit ->
                    if (
                        isPlaying &&
                        currentGuess.size < difficulty.codeLength &&
                        digit !in currentGuess
                    ) {
                        currentGuess = currentGuess + digit
                    }
                },

                onDeleteClick = {
                    if (isPlaying && currentGuess.isNotEmpty()) {
                        currentGuess = currentGuess.dropLast(1)
                    }
                },

                onSubmitClick = {
                    if (
                        isPlaying &&
                        currentGuess.size == difficulty.codeLength
                    ) {
                        session = session.submitGuess(currentGuess)
                        currentGuess = emptyList()
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 400.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    if (session.status != GameStatus.IN_PROGRESS) {

        val isWin = session.status == GameStatus.WON

        AlertDialog(
            onDismissRequest = {},

            title = {
                Text(
                    text = stringResource(
                        if (isWin) R.string.game_win_title
                        else R.string.game_lose_title
                    )
                )
            },

            text = {
                Text(
                    text = if (isWin) {
                        stringResource(R.string.game_win_message)
                    } else {
                        stringResource(
                            R.string.game_lose_message,
                            session.secretCode.joinToString("")
                        )
                    }
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        session = GameSession.start(difficulty)
                        currentGuess = emptyList()
                    }
                ) {
                    Text(stringResource(R.string.game_retry))
                }
            },

            dismissButton = {
                TextButton(onClick = onHomeClick) {
                    Text(stringResource(R.string.game_home))
                }
            }
        )
    }
}

@Composable
private fun GameHeaderButton(
    text: String,
    onClick: () -> Unit
) {
    val fontFamily = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF66563D))
            .clickable(onClick = onClick)
            .padding(
                horizontal = 12.dp,
                vertical = 9.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = 13.sp
        )
    }
}
