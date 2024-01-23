package com.example.jetpack.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.R
import com.example.jetpack.theme.regularPoppFamily
import com.example.jetpack.theme.semiPoppFamily


@Composable
fun HeaderView(headerTitle: String, headerTitle1: String) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.loginbackgroudn),
        contentScale = ContentScale.FillWidth,
        contentDescription = "header_view_login_bg"
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp)
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .width(270.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.search_icon),
                contentDescription = "header_view_flower_logo"
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp),
            text = headerTitle,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = semiPoppFamily,
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            textAlign = TextAlign.Center,
            text = headerTitle1,
            color = Color.White,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = regularPoppFamily,
            )
        )
    }
}
