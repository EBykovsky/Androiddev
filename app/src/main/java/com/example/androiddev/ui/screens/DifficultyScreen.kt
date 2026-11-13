package com.example.androiddev.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddev.R
import com.example.androiddev.domain.GameDifficulty
import com.example.androiddev.ui.components.GameMenuButton
import com.example.androiddev.ui.components.GameMenuLayout

@Composable
fun DifficultyScreen(
    onDifficultySelected: (GameDifficulty) -> Unit
) {
    GameMenuLayout { scale ->

        val fontFamily = FontFamily(
            Font(R.font.rubik_one_regular)
        )

        Text(
            text = stringResource(R.string.difficulty_title),
            color = Color(0xFF66563D),
            fontFamily = fontFamily,
            fontSize = (30f * scale).sp,
            lineHeight = (36f * scale).sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (365f * scale).dp)
                .width((276f * scale).dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (460f * scale).dp),
            verticalArrangement = Arrangement.spacedBy(
                (12f * scale).dp
            )
        ) {
            GameMenuButton(
                text = stringResource(R.string.difficulty_normal),
                scale = scale,
                onClick = {
                    onDifficultySelected(GameDifficulty.NORMAL)
                }
            )

            GameMenuButton(
                text = stringResource(R.string.difficulty_hard),
                scale = scale,
                onClick = {
                    onDifficultySelected(GameDifficulty.HARD)
                }
            )
        }
    }
}