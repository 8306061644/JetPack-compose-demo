package com.example.jetpack.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.R
import com.example.jetpack.theme.ADD_NEW
import com.example.jetpack.theme.SEARCH_PASSWORD
import com.example.jetpack.theme.colorPrimary
import com.example.jetpack.theme.placeholderColor
import com.example.jetpack.theme.regularPoppFamily
import com.example.jetpack.theme.semiPoppFamily
import com.example.jetpack.theme.sign_phone_back_color


@Composable
fun searchView(
    topMargin: Dp,
    screenName: String,
    context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topMargin, bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 10.dp, start = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50.dp))
                    .background(sign_phone_back_color)
                    .padding(end = 10.dp, start = 10.dp)
                    .size(40.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {

                Image(
                    modifier = Modifier.size(14.dp),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.search_icon),
                    contentDescription = "password_card"
                )

                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = SEARCH_PASSWORD,
                    style = TextStyle(
                        color = placeholderColor,
                        fontSize = 14.sp,
                        fontFamily = regularPoppFamily
                    )
                )
            }
        }


        if (screenName.equals("removeAddnew")) {

        } else {

            Row(
                modifier = Modifier
                    .weight(0.5f)
                    .clip(RoundedCornerShape(50.dp))
                    .background(colorPrimary)
                    .size(40.dp)
                    .clickable(onClick = {

                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.size(10.dp),
                    bitmap = ImageBitmap.imageResource(id = R.drawable.plus),
                    contentDescription = "password_card"
                )
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = ADD_NEW,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = semiPoppFamily
                    )
                )
            }

        }

    }
}
