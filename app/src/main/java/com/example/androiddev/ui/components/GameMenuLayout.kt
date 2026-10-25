
package com.example.androiddev.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddev.R

@Composable
fun GameMenuLayout(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true,
    frameHeight: Float = 466f,
    content: @Composable BoxScope.(Float) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val scale = minOf(
            1f,
            maxWidth.value / 411f,
            maxHeight.value / 891f
        )

        if (showBackground) {
            Image(
                painter = painterResource(R.drawable.game_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

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
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (156f * scale).dp)
                    .size(
                        width = (328f * scale).dp,
                        height = (frameHeight * scale).dp
                    )
            )

            Image(
                painter = painterResource(R.drawable.game_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (110f * scale).dp)
                    .size(
                        width = (371f * scale).dp,
                        height = (247f * scale).dp
                    )
            )

            content(scale)
        }
    }
}
