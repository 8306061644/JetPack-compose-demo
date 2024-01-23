package com.example.jetpack.ui.activity

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.navigation.Destination
import com.example.jetpack.ui.activity.dashboard.DashboardScreen
import com.example.jetpack.ui.activity.dashboard.DashboardViewModel


@Composable
fun ComposeApp(context: Context, viewModel: DashboardViewModel) {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = Destination.DashBoard) {

            composable(Destination.DashBoard) {
                DashboardScreen(context, viewModel)
            }

        }
    }
}