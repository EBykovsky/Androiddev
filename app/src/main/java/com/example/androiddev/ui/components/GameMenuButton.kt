
package com.example.androiddev.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddev.R

@Composable
fun GameMenuButton(
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
