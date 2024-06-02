package com.birukb.biruksart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium, // Adjusted for a bit more emphasis
        fontSize = 24.sp, // Slightly larger size for titles
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium, // Suitable for smaller labels
        fontSize = 12.sp, // Adjusted for better readability
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(  // New style for app title or major headers
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold, // Bold weight for headline emphasis
        fontSize = 30.sp, // Larger font size for clear headline visibility
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )
)
