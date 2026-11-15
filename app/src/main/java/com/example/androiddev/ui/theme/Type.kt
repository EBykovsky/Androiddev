package com.example.androiddev.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.androiddev.R

val GameFontFamily = FontFamily(
    Font(R.font.rubik_one_regular)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = GameFontFamily,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        color = FarmBrownDark
    ),

    titleMedium = TextStyle(
        fontFamily = GameFontFamily,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        color = FarmBrownDark
    ),

    labelLarge = TextStyle(
        fontFamily = GameFontFamily,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = FarmBrownDark
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = FarmBrownDark
    )
)