package com.example.jetpack.ui.fragment

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.component.searchView
import com.example.jetpack.navigation.Destination.AddNewCredential
import com.example.jetpack.theme.mediumPoppFamily
import com.example.jetpack.theme.semiPoppFamily
import com.example.jetpack.theme.textColor
import com.example.jetpack.ui.activity.dashboard.DashboardViewModel
import com.example.jetpack.ui.fragment.homePasswordModel.PasswordModel
import com.example.jetpack.ui.fragment.homePasswordModel.PasswordModelData

@Composable
fun HomeFragment(
    context: Context, viewModel: DashboardViewModel
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 10.dp, end = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            horizontalPasswordScroll()
        }
        item {
            searchView(45.dp, AddNewCredential, context)
        }

    }
}

@Composable
private fun horizontalPasswordScroll() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        items(PasswordModelData.list.size) {
            PasswordCard(PasswordModelData.list[it])
        }
    }
}



@Composable
private fun PasswordCard(passwordModel: PasswordModel) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(start = 15.dp)
            .width(140.dp)
            .height(155.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {

            Image(
                modifier = Modifier.size(24.dp),
                bitmap = ImageBitmap.imageResource(id = passwordModel.image),
                contentDescription = "password_card"
            )
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = passwordModel.title,
                style = TextStyle(
                    color = textColor, fontSize = 14.sp, fontFamily = mediumPoppFamily
                )
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = passwordModel.dayCount,
                style = TextStyle(
                    color = Color.Black, fontSize = 20.sp, fontFamily = semiPoppFamily
                )
            )

        }
    }
}