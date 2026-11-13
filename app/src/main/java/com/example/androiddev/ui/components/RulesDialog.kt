
package com.example.androiddev.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.androiddev.R

@Composable
fun RulesDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        GameMenuLayout(
            showBackground = false,
            frameHeight = 585f
        ) { scale ->

            val fontFamily = FontFamily(
                Font(R.font.rubik_one_regular)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (365f * scale).dp)
                    .width((265f * scale).dp),
                verticalArrangement = Arrangement.spacedBy(
                    (11f * scale).dp
                )
            ) {
                Text(
                    text = stringResource(R.string.rules_title),
                    color = Color(0xFF66563D),
                    fontFamily = fontFamily,
                    fontSize = (17f * scale).sp
                )

                val rules = listOf(
                    stringResource(R.string.rules_intro),
                    stringResource(R.string.rules_input),
                    stringResource(R.string.rules_bull),
                    stringResource(R.string.rules_cow),
                    stringResource(R.string.rules_goal)
                )

                rules.forEach { rule ->
                    Text(
                        text = rule,
                        color = Color(0xFF66563D),
                        fontFamily = fontFamily,
                        fontSize = (14f * scale).sp,
                        lineHeight = (18f * scale).sp
                    )
                }
            }

            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (650f * scale).dp)
            ) {
                GameMenuButton(
                    text = stringResource(R.string.rules_close),
                    scale = scale,
                    onClick = onDismiss
                )
            }
        }
    }
}
