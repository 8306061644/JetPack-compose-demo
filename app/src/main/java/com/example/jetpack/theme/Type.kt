package com.example.jetpack.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jetpack.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppinssemibold))
    ),
    subtitle1 = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.poppinssemibold))
    ),
    subtitle2 = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.poppinssemibold))
    )

/* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)