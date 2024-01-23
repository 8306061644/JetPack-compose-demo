package com.example.jetpack

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.jetpack.helper.PreferenceHelper
import com.example.jetpack.ui.activity.ComposeApp
import com.example.jetpack.ui.activity.dashboard.DashboardViewModel
import com.example.jetpack.ui.base.BaseComponentActivity

class MainActivity : BaseComponentActivity<DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()

    @Composable
    override fun ProvideCompose() {
//        intent.getStringExtra("mobileNo")

        var txtMobileNo by remember { mutableStateOf(intent.getStringExtra("mobileNo")) }
        var prefHelper = PreferenceHelper(this)
        prefHelper.mobileno = txtMobileNo!!
        ComposeApp(this, viewModel)
    }


    @Composable
    override fun ProvideComposeLightPreview() {

    }
}

