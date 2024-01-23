package com.example.jetpack.ui.activity.dashboard

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.jetpack.R
import com.example.jetpack.component.CustomTopAppBar
import com.example.jetpack.ui.fragment.CredentialFragment
import com.example.jetpack.ui.fragment.HomeFragment
import com.example.jetpack.ui.fragment.UserFragment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    context: Context,
    viewModel: DashboardViewModel
) {
    val selectedIndex = remember { mutableStateOf(0) }
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                CustomTopAppBar(R.drawable.header_menu, R.drawable.header_profile, context)
            },
            content = {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    Box(modifier = Modifier.padding(bottom = 96.dp)) {
                        when (selectedIndex.value) {
                            0 -> {
                                HomeFragment(context, viewModel)
                            }
                            1 -> {
                                CredentialFragment(context, viewModel)
                            }
                            2 -> {
                                UserFragment(context, viewModel)
                            }
                            3 -> {
                            }

                        }
                    }
                }
            },
            bottomBar = {
                CustomBottomBar(selectedIndex = selectedIndex)
            },

            )
    }
}

@Composable
fun CustomBottomBar(selectedIndex: MutableState<Int>) {

    val listItems = listOf("Home", "Location", "Cart", "Profile")

    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(64.dp),
    ) {
        BottomNavigation(backgroundColor = Color.Black) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.home),
                                    isSelected
                                )
                            }
                            1 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.connection),
                                    isSelected
                                )
                            }
                            2 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.message),
                                    isSelected
                                )
                            }
                            3 -> {
                                TabIcons(
                                    ImageBitmap.imageResource(id = R.drawable.blutooth),
                                    isSelected
                                )
                            }
                        }
                    },
                    selected = isSelected,
                    onClick = { selectedIndex.value = index },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

@Composable
fun TabIcons(icon: ImageBitmap, isTintColor: Boolean) {
    if (isTintColor) {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .size(24.dp),
            bitmap = icon,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_if"
        )
    } else {
        Image(
            modifier = Modifier
                .wrapContentSize()
                .size(24.dp),
            bitmap = icon,
            contentScale = ContentScale.Fit,
            contentDescription = "tb_icon_else"
        )
    }
}



