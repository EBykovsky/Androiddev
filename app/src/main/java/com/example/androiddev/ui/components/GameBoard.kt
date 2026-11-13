
package com.example.androiddev.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddev.R
import com.example.androiddev.domain.DigitResult
import com.example.androiddev.domain.GameDifficulty
import com.example.androiddev.domain.GuessResult

private enum class CellAppearance {
    DISABLED,
    NORMAL,
    BULL,
    COW
}

@Composable
fun GameBoard(
    difficulty: GameDifficulty,
    attempts: List<GuessResult>,
    currentGuess: List<Int>,
    modifier: Modifier = Modifier
) {
    val fontFamily = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.72f)
                .height(44.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp
                    )
                )
                .background(Color(0xFF66563D)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.game_guess_title),
                color = Color.White,
                fontFamily = fontFamily,
                fontSize = 23.sp,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(
                    width = 5.dp,
                    color = Color(0xFF66563D),
                    shape = RoundedCornerShape(25.dp)
                )
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFFF9EFBE))
                .padding(9.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                repeat(difficulty.maxAttempts) { rowIndex ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement =
                            Arrangement.spacedBy(4.dp)
                    ) {

                        repeat(difficulty.codeLength) { columnIndex ->

                            val submitted = attempts.getOrNull(rowIndex)

                            val digit = when {
                                submitted != null ->
                                    submitted.guess[columnIndex]

                                rowIndex == attempts.size ->
                                    currentGuess.getOrNull(columnIndex)

                                else -> null
                            }

                            val appearance = when {
                                submitted != null -> {
                                    when (submitted.digitResults[columnIndex]) {
                                        DigitResult.BULL ->
                                            CellAppearance.BULL

                                        DigitResult.COW ->
                                            CellAppearance.COW

                                        DigitResult.MISS ->
                                            CellAppearance.NORMAL
                                    }
                                }

                                rowIndex == attempts.size ->
                                    CellAppearance.NORMAL

                                else ->
                                    CellAppearance.DISABLED
                            }

                            val showCursor =
                                rowIndex == attempts.size &&
                                        columnIndex == currentGuess.size

                            GameCell(
                                digit = digit,
                                showCursor = showCursor,
                                appearance = appearance,
                                isHard = difficulty == GameDifficulty.HARD,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GameCell(
    digit: Int?,
    showCursor: Boolean,
    appearance: CellAppearance,
    isHard: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (appearance) {
        CellAppearance.DISABLED -> Color(0xFFE5E5E5)
        CellAppearance.NORMAL -> Color(0xFFFFFAE4)
        CellAppearance.BULL -> Color(0xFFA4F5BA)
        CellAppearance.COW -> Color(0xFFFFEA83)
    }

    val borderColor = when (appearance) {
        CellAppearance.DISABLED -> Color(0xFFAFAFAF)
        CellAppearance.NORMAL -> Color(0xFFCDBB9E)
        CellAppearance.BULL -> Color(0xFF7CCD92)
        CellAppearance.COW -> Color(0xFFE1CC65)
    }

    val text = when {
        digit != null -> digit.toString()
        showCursor -> "−"
        else -> ""
    }

    val fontFamily = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    val fontSize = if (isHard) 26.sp else 40.sp
    val cornerRadius = if (isHard) 13.dp else 10.dp

    val outlineWidth = with(LocalDensity.current) {
        4.dp.toPx()
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .border(
                1.dp,
                borderColor,
                RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = Color(0xFF433722),
            fontFamily = fontFamily,
            fontSize = fontSize,
            maxLines = 1,
            style = TextStyle(
                drawStyle = Stroke(
                    width = outlineWidth,
                    join = StrokeJoin.Round
                )
            )
        )

        Text(
            text = text,
            color = Color.White,
            fontFamily = fontFamily,
            fontSize = fontSize,
            maxLines = 1
        )
    }
}
