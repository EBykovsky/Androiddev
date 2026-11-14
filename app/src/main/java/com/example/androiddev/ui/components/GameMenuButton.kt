
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import com.example.androiddev.ui.theme.FarmBrown
import com.example.androiddev.ui.theme.FarmBrownDark
import com.example.androiddev.ui.theme.GameFontFamily
import androidx.compose.runtime.remember
@Composable
fun GameMenuButton(
    text: String,
    scale: Float,
    onClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    val buttonScale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        label = "gameMenuButtonScale"
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
            .scale(buttonScale)
            .clip(
                RoundedCornerShape((10f * scale).dp)
            )
            .background(
                if (isPressed) FarmBrownDark else FarmBrown
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = FarmBrownDark,
            fontFamily = GameFontFamily,
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
            fontFamily = GameFontFamily,
            fontSize = (36f * scale).sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false
        )
    }
}
