package com.example.jetpack.ui.fragment

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.component.searchView
import com.example.jetpack.navigation.Destination.AddNewUser
import com.example.jetpack.theme.semiPoppFamily
import com.example.jetpack.ui.activity.dashboard.DashboardViewModel

@Composable
fun UserFragment(context: Context, viewModel: DashboardViewModel) {


//    val userDirectory = viewModel.userdirectoryStateDataStateData.collectAsState().value
//
//    OnLifecycleEvent { owner, event ->
//        // do stuff on event
//        when (event) {
//            Lifecycle.Event.ON_RESUME -> {
//                viewModel.getuserdirectory(context, "")
//            }
//            else -> {}
//        }
//    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = 10.dp, end = 10.dp),
    ) {
        item {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                text = "User Directory",
                textAlign = TextAlign.Start,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontFamily = semiPoppFamily
                )
            )
        }
        item {
            searchView(25.dp, AddNewUser, context)
        }
       

    }
}
