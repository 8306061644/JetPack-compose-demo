package com.example.jetpack.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.jetpack.R

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val semiPoppFamily = FontFamily(
    Font(R.font.poppinssemibold)

)

val mediumPoppFamily = FontFamily(
    Font(R.font.poppinsmedium)
)

val regularPoppFamily = FontFamily(
    Font(R.font.poppinsregular)
)


@Composable
fun JetPackTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

private val ColorPalette = lightColors(
    primary = colorPrimary,
    primaryVariant = colorPrimary,
    secondary = colorPrimary

)

@Composable
fun JetPackTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
        colors = ColorPalette,
        typography = typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}