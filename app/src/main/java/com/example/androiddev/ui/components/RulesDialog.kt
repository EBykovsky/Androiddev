
package com.example.androiddev.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.androiddev.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.androiddev.ui.theme.FarmBrown
import com.example.androiddev.ui.theme.FarmBullGreen
import com.example.androiddev.ui.theme.FarmCowYellow
import com.example.androiddev.ui.theme.GameFontFamily
import androidx.compose.foundation.border

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
                    color = FarmBrown,
                    fontFamily = GameFontFamily,
                    fontSize = (17f * scale).sp
                )

                val rules = listOf(
                    stringResource(R.string.rules_intro),
                    stringResource(R.string.rules_input),
                    stringResource(R.string.rules_bull),
                    stringResource(R.string.rules_cow),
                    stringResource(R.string.rules_goal)
                )

                rules.forEachIndexed { index, rule ->

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            (6f * scale).dp
                        ),
                        verticalAlignment = Alignment.Top
                    ) {

                        if (index == 2 || index == 3) {

                            val markerColor = if (index == 2) {
                                FarmBullGreen
                            } else {
                                FarmCowYellow
                            }

                            Box(
                                modifier = Modifier
                                    .size((14f * scale).dp)
                                    .background(
                                        color = markerColor,
                                        shape = RoundedCornerShape((4f * scale).dp)
                                    )
                                    .border(
                                        width = (1.5f * scale).dp,
                                        color = FarmBrown,
                                        shape = RoundedCornerShape((4f * scale).dp)
                                    )
                            )
                        }

                        Text(
                            text = rule,
                            color = FarmBrown,
                            fontFamily = GameFontFamily,
                            fontSize = (14f * scale).sp,
                            lineHeight = (18f * scale).sp
                        )
                    }
                }
            }

            Box(
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
