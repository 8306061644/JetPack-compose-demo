package com.example.jetpack.ui.activity.masterkey

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack.component.HeaderView
import com.example.jetpack.component.SmsCodeView
import com.example.jetpack.theme.JetPackTheme
import com.example.jetpack.theme.MASTER_KEY
import com.example.jetpack.theme.NEXT
import com.example.jetpack.theme.SETUP_MASTER_KEY
import com.example.jetpack.theme.SIGN_IN_JETPACK
import com.example.jetpack.theme.mediumPoppFamily
import com.example.jetpack.theme.resendColor
import com.example.jetpack.ui.activity.otpkey.MasterActivity
import com.example.jetpack.ui.activity.otpkey.MasterKeyViewModel
import com.example.jetpack.ui.base.BaseComponentActivity

class SetupMasterActivity : BaseComponentActivity<MasterKeyViewModel>() {

    override val viewModel: MasterKeyViewModel by viewModels()

    @Composable
    override fun ProvideCompose() {

        var txtMobileNo by remember { mutableStateOf(intent.getStringExtra("mobileNo")) }
        var txtSetupMasterKey by remember { mutableStateOf("") }

        SetMasterCompose {
            JetPackTheme {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        ConstraintLayout {
                            val (image, loginForm) = createRefs()
                            Box(
                                modifier = Modifier
                                    .height(350.dp)
                                    .constrainAs(image) {
                                        top.linkTo(loginForm.top)
                                        bottom.linkTo(loginForm.top)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    }) {
                                HeaderView(SIGN_IN_JETPACK, SETUP_MASTER_KEY)
                            }

                            Surface(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                                    .padding(top = 100.dp)
                                    .constrainAs(loginForm) {
                                        bottom.linkTo(parent.bottom)
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                    },
                                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxSize()
                                        .padding(20.dp),
                                ) {

                                    Text(
                                        text = MASTER_KEY,
                                        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
                                        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
                                        fontFamily = mediumPoppFamily,
                                        fontSize = 14.sp
                                    )

                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {

                                        SmsCodeView(
                                            smsCodeLength = 4,
                                            boaderColor = resendColor,
                                            cursorColor = resendColor
                                        ) {
                                            txtSetupMasterKey = it
                                        }
                                    }

                                    RegistrationButton {
                                        val intent = Intent(
                                            this@SetupMasterActivity,
                                            MasterActivity::class.java
                                        )
                                        intent.putExtra("mobileNo", txtMobileNo!!.trim())
                                        intent.putExtra("setup", "setupActivity")
                                        intent.putExtra("setupKey", txtSetupMasterKey)
                                        startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun RegistrationButton(onClick: () -> Unit = {}) {

        Button(
            onClick = { onClick() },
            modifier = Modifier
                .padding(top = 30.dp, bottom = 34.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = NEXT,
                color = Color.White,
                style = MaterialTheme.typography.button
            )
        }

    }

    @Composable
    fun SetMasterCompose(childrenCompose: @Composable () -> Unit) {

        childrenCompose()

    }

    @Composable
    override fun ProvideComposeLightPreview() {
        JetPackTheme {
            SetMasterCompose {

            }

        }
    }

}
