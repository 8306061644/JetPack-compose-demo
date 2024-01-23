package com.example.jetpack.ui.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack.helper.PreferenceHelper
import com.example.jetpack.theme.JetPackTheme
import com.example.jetpack.theme.ONE_TAP_CALL
import com.example.jetpack.theme.semiPoppFamily
import com.example.jetpack.ui.activity.login.LoginActivity
import com.example.jetpack.ui.base.BaseComponentActivity
import com.example.jetpack.utils.randomCharString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SplashWaitTime: Long = 3000

@ExperimentalFoundationApi
class SplashActivity : BaseComponentActivity<SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override val wantToShowCustomLoading = true


    @Composable
    override fun ProvideCompose() {

        SplashCompose {
            var prefHelper = PreferenceHelper(this)
            if (prefHelper.plaformId.equals("")) {
                prefHelper.plaformId = randomCharString(16)
            }
            loadUi()

        }
    }

    @Composable
    private fun SplashCompose(ChildrenCompose: @Composable () -> Unit) {
        ChildrenCompose()

    }


    @Composable
    private fun ImageAndAppName(showLoading: @Composable () -> Unit) {

        loadUi()
    }

    @Composable
    fun loadUi() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 50.dp),
            contentAlignment = Alignment.BottomCenter,

            ) {
            Greeting()
            LaunchedEffect(key1 = true) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(SplashWaitTime)
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    @Composable
    fun Greeting() {
        Text(
            text = ONE_TAP_CALL,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = semiPoppFamily
        )
    }

    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        JetPackTheme {
            SplashCompose {
                ImageAndAppName {
                    CircularProgressIndicator()

                }
            }
        }
    }
}