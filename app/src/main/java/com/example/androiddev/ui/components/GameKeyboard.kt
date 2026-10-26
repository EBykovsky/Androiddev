
package com.example.androiddev.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddev.R
import androidx.compose.foundation.layout.width

@Composable
fun GameKeyboard(
    currentGuess: List<Int>,
    codeLength: Int,
    onDigitClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onSubmitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val digits = listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(6, 7, 8, 9, 0)
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        digits.forEach { rowDigits ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                rowDigits.forEach { digit ->

                    KeyboardKey(
                        text = digit.toString(),
                        enabled = currentGuess.size < codeLength &&
                                digit !in currentGuess,
                        onClick = { onDigitClick(digit) },
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                    )
                }
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            KeyboardKey(
                text = stringResource(R.string.keyboard_submit),
                enabled = currentGuess.size == codeLength,
                onClick = onSubmitClick,
                modifier = Modifier
                    .width(160.dp)
                    .height(52.dp),
                fontSize = 24
            )

            val deleteDescription =
                stringResource(R.string.keyboard_delete)

            KeyboardKey(
                text = "⌫",
                enabled = currentGuess.isNotEmpty(),
                onClick = onDeleteClick,
                modifier = Modifier
                    .width(52.dp)
                    .height(52.dp)
                    .semantics {
                        contentDescription = deleteDescription
                    },
                fontSize = 26
            )
        }
    }
}

@Composable
private fun KeyboardKey(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: Int = 30
) {
    val fontFamily = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    val outlineWidth = with(LocalDensity.current) {
        2.dp.toPx()
    }

    Box(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.55f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF66563D))
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = Color(0xFF3D301E),
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center,
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
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
