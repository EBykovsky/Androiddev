
package com.example.androiddev.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddev.R
import com.example.androiddev.ui.components.GameMenuButton
import com.example.androiddev.ui.components.GameMenuLayout

@Composable
fun SettingsScreen(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    GameMenuLayout(
        modifier = modifier,
        frameHeight = 410f,
        artworkOffsetY = 0f
    ) { scale ->

        val fontFamily = FontFamily(
            Font(R.font.rubik_one_regular)
        )

        val textColor = Color(0xFF66563D)

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (355f * scale).dp)
                .width((260f * scale).dp),
            verticalArrangement = Arrangement.spacedBy(
                (5f * scale).dp
            )
        ) {
            Text(
                text = stringResource(R.string.settings_title),
                color = textColor,
                fontFamily = fontFamily,
                fontSize = (17f * scale).sp
            )

            Text(
                text = stringResource(R.string.settings_language),
                color = textColor,
                fontFamily = fontFamily,
                fontSize = (15f * scale).sp
            )

            LanguageOption(
                text = stringResource(
                    R.string.settings_russian
                ),
                selected = selectedLanguage == "ru",
                scale = scale,
                onClick = {
                    onLanguageSelected("ru")
                }
            )

            LanguageOption(
                text = stringResource(
                    R.string.settings_english
                ),
                selected = selectedLanguage == "en",
                scale = scale,
                onClick = {
                    onLanguageSelected("en")
                }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (480f * scale).dp)
        ) {
            GameMenuButton(
                text = stringResource(R.string.settings_save),
                scale = scale,
                onClick = onSave
            )
        }
    }
}

@Composable
private fun LanguageOption(
    text: String,
    selected: Boolean,
    scale: Float,
    onClick: () -> Unit
) {
    val color = Color(0xFF66563D)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            ),
        horizontalArrangement = Arrangement.spacedBy(
            (8f * scale).dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size((20f * scale).dp)
                .border(
                    width = (2f * scale).dp,
                    color = color,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size((10f * scale).dp)
                        .background(
                            color = color,
                            shape = CircleShape
                        )
                )
            }
        }

        Text(
            text = text,
            color = color,
            fontFamily = FontFamily(
                Font(R.font.rubik_one_regular)
            ),
            fontSize = (15f * scale).sp
        )
    }
}
