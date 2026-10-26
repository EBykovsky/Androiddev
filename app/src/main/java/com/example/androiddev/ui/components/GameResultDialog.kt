
package com.example.androiddev.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.androiddev.R
import com.example.androiddev.domain.GameStatus

@Composable
fun GameResultDialog(
    status: GameStatus,
    secretCode: List<Int>,
    attemptsCount: Int,
    onRetry: () -> Unit,
    onHome: () -> Unit
) {
    val isWin = status == GameStatus.WON
    val code = secretCode.joinToString("")

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        GameMenuLayout(
            showBackground = false,
            frameHeight = 562f
        ) { scale ->

            val fontFamily = FontFamily(
                Font(R.font.rubik_one_regular)
            )

            Text(
                text = stringResource(
                    if (isWin) R.string.game_win_title
                    else R.string.game_lose_title
                ),
                color = Color(0xFF66563D),
                fontFamily = fontFamily,
                fontSize = (32f * scale).sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (385f * scale).dp)
                    .width((285f * scale).dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (448f * scale).dp)
                    .width((285f * scale).dp),
                verticalArrangement = Arrangement.spacedBy(
                    (12f * scale).dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isWin) {
                        stringResource(R.string.game_win_code, code)
                    } else {
                        stringResource(R.string.game_lose_code, code)
                    },
                    color = Color(0xFF66563D),
                    fontFamily = fontFamily,
                    fontSize = (32f * scale).sp,
                    textAlign = TextAlign.Center
                )

                if (isWin) {
                    Text(
                        text = stringResource(
                            R.string.game_win_attempts,
                            attemptsCount
                        ),
                        color = Color(0xFF66563D),
                        fontFamily = fontFamily,
                        fontSize = (32f * scale).sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (555f * scale).dp)
            ) {
                GameMenuButton(
                    text = stringResource(R.string.game_retry),
                    scale = scale,
                    onClick = onRetry
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (630f * scale).dp)
            ) {
                GameMenuButton(
                    text = stringResource(R.string.game_home),
                    scale = scale,
                    onClick = onHome
                )
            }
        }
    }
}
