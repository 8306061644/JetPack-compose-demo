package com.example.jetpack.ui.activity.otp

import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpack.component.HeaderView
import com.example.jetpack.component.SmsCodeView
import com.example.jetpack.helper.PreferenceHelper
import com.example.jetpack.theme.JetPackTheme
import com.example.jetpack.theme.NEXT
import com.example.jetpack.theme.OTP
import com.example.jetpack.theme.PLEASE_ENTER_OPT
import com.example.jetpack.theme.RESEND_OTP
import com.example.jetpack.theme.SIGN_IN_JETPACK
import com.example.jetpack.theme.colorPrimary
import com.example.jetpack.theme.mediumPoppFamily
import com.example.jetpack.theme.resendColor
import com.example.jetpack.ui.activity.masterkey.SetupMasterActivity
import com.example.jetpack.ui.base.BaseComponentActivity
import com.example.jetpack.utils.ENTER_MOBILE_NUMBER
import com.example.jetpack.utils.NO_INTERNET_CONNECTION
import com.example.jetpack.utils.NetworkUtils
import com.example.jetpack.utils.showToast
import org.json.JSONObject

class OTPActivity : BaseComponentActivity<OTPViewModel>() {

    override val viewModel: OTPViewModel by viewModels()

    @Composable
    override fun ProvideCompose() {

        var prefHelper = PreferenceHelper(this)


        OtpCompose {
            var txtOtp by remember { mutableStateOf("") }
            var txtMobileNo by remember { mutableStateOf(intent.getStringExtra("mobileNo")) }

            viewModel.otpResponse.observe(this,
                androidx.lifecycle.Observer {
                    try {

                        val intent = Intent(this, SetupMasterActivity::class.java)
                        intent.putExtra("mobileNo", txtMobileNo!!.trim())
                        startActivity(intent)

//                        val obj = JSONObject(it)
//                        if (!obj.getBoolean("newuser")) {
//                            val intent = Intent(this, MasterActivity::class.java)
//                            intent.putExtra("mobileNo", txtMobileNo!!.trim())
//                            startActivity(intent)
//                        } else {
//                            val intent = Intent(this, SetupMasterActivity::class.java)
//                            intent.putExtra("mobileNo", txtMobileNo!!.trim())
//                            startActivity(intent)
//                        }
//                        prefHelper.clearTimeCalculation()
//                        showToast(this, obj.getString("meesage"))

                    } catch (tx: Throwable) {
                        Log.e(
                            "My App",
                            "Could not parse malformed JSON: "
                        )
                    }

                })

            viewModel.otpResendResponse.observe(this,
                androidx.lifecycle.Observer {
                    try {
                        val obj = JSONObject(it)
                        if (!obj.getBoolean("newuser")) {
                            showToast(this, "Decryption error")
                        } else {
                            if (obj.getBoolean("newuser")) {
                                showToast(this, obj.getString("meesage"))
                            } else {
                                showToast(this, "Decryption error")
                            }
                        }
                    } catch (tx: Throwable) {
                        Log.e(
                            "My App",
                            "Could not parse malformed JSON: "
                        )
                    }

                })

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
                                HeaderView(SIGN_IN_JETPACK, PLEASE_ENTER_OPT + txtMobileNo)
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
                                        text = OTP,
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
                                            boaderColor = colorPrimary,
                                            cursorColor = colorPrimary
                                        ) {
                                            txtOtp = it
                                        }
                                    }

                                    RegistrationButton {
                                        if (NetworkUtils.isNetworkAvailable(this@OTPActivity)) {
                                            if (txtOtp.isEmpty()) {
                                                showToast(this@OTPActivity, ENTER_MOBILE_NUMBER)
                                            } else {
                                                viewModel.onOtpBtnClick(
                                                    this@OTPActivity,
                                                    "91$txtMobileNo",
                                                    txtOtp.trim(),
                                                    ""
                                                )
                                            }
                                        }else{
                                            showToast(this@OTPActivity, NO_INTERNET_CONNECTION)
                                        }

                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxSize()
                                            .clickable {
                                                viewModel.onResendOtpBtnClick(
                                                    this@OTPActivity,
                                                    "91$txtMobileNo",
                                                    ""
                                                )
                                            },
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = RESEND_OTP,
                                            style = MaterialTheme.typography.subtitle1.copy(color = resendColor),
                                            modifier = Modifier.padding(
                                                bottom = 10.dp,
                                                top = 40.dp
                                            ),
                                            fontFamily = mediumPoppFamily,
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center
                                        )
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
    fun OtpCompose(childrenCompose: @Composable () -> Unit) {

        childrenCompose()

    }

    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        JetPackTheme {
            OtpCompose {

            }

        }
    }

}
