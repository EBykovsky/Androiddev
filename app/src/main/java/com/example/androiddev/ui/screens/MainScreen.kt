
package com.example.androiddev.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import com.example.androiddev.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartClick: () -> Unit,
    onRulesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {

        val scale = minOf(
            1f,
            maxWidth.value / 411f,
            maxHeight.value / 891f
        )

        // Фон занимает весь экран.
        Image(
            painter = painterResource(R.drawable.game_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .size(
                    width = (411f * scale).dp,
                    height = (891f * scale).dp
                )
                .align(Alignment.Center)
        ) {

            Image(
                painter = painterResource(R.drawable.game_frame),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (156f * scale).dp)
                    .size(
                        width = (328f * scale).dp,
                        height = (466f * scale).dp
                    )
            )

            Image(
                painter = painterResource(R.drawable.game_logo),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (110f * scale).dp)
                    .size(
                        width = (371f * scale).dp,
                        height = (247f * scale).dp
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (376f * scale).dp),
                verticalArrangement = Arrangement.spacedBy(
                    (14f * scale).dp
                )
            ) {

                MainMenuButton(
                    text = stringResource(R.string.menu_start),
                    scale = scale,
                    onClick = onStartClick
                )

                MainMenuButton(
                    text = stringResource(R.string.menu_rules),
                    scale = scale,
                    onClick = onRulesClick
                )

                MainMenuButton(
                    text = stringResource(R.string.menu_settings),
                    scale = scale,
                    onClick = onSettingsClick
                )
            }
        }
    }
}

@Composable
private fun MainMenuButton(
    text: String,
    scale: Float,
    onClick: () -> Unit
) {
    val buttonColor = Color(0xFF66563D)
    val outlineColor = Color(0xFF3D301E)

    val fontFamily = FontFamily(
        Font(R.font.rubik_one_regular)
    )

    val outlineWidth = with(LocalDensity.current) {
        (3f * scale).dp.toPx()
    }

    Box(
        modifier = Modifier
            .size(
                width = (276f * scale).dp,
                height = (63f * scale).dp
            )
            .clip(RoundedCornerShape((10f * scale).dp))
            .background(buttonColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = outlineColor,
            fontFamily = fontFamily,
            fontSize = (36f * scale).sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false,
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
            fontSize = (36f * scale).sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false
        )
    }
}
