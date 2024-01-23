package com.example.jetpack.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopAppBar(leftImage: Int, rightImage: Int, navController: Context) {
    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {

                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = { }
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = leftImage),
                        contentDescription = "dashboard_menu"
                    )
                }
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { }
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = rightImage),
                        contentDescription = "dashboard_profile"
                    )
                }
            }
        },
        backgroundColor = Color.Transparent,
    )
}