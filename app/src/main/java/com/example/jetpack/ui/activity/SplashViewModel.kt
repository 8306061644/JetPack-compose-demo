package com.example.jetpack.ui.activity

import com.example.jetpack.ui.base.BaseViewModel
import kotlinx.coroutines.FlowPreview

class SplashViewModel : BaseViewModel() {


    @FlowPreview
    fun decideActivity() {
        showLoading()

    }


}