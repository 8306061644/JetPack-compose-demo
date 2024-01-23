package com.example.jetpack.ui.activity.login

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import com.example.jetpack.R
import com.example.jetpack.component.HeaderView
import com.example.jetpack.helper.PreferenceHelper
import com.example.jetpack.security.Authentication
import com.example.jetpack.theme.JetPackTheme
import com.example.jetpack.theme.ENTER_PHONE_NUMBER_OTP
import com.example.jetpack.theme.NEXT
import com.example.jetpack.theme.SIGN_IN_JETPACK
import com.example.jetpack.theme.login
import com.example.jetpack.theme.mediumPoppFamily
import com.example.jetpack.theme.placeholderColor
import com.example.jetpack.theme.sign_phone_back_color
import com.example.jetpack.ui.activity.otp.OTPActivity
import com.example.jetpack.ui.base.BaseComponentActivity
import com.example.jetpack.utils.ENTER_MOBILE_NUMBER
import com.example.jetpack.utils.ENTER_VALIDE_MOBILE_NUMBER
import com.example.jetpack.utils.NO_INTERNET_CONNECTION
import com.example.jetpack.utils.NetworkUtils
import com.example.jetpack.utils.showToast
import java.util.concurrent.TimeUnit


class LoginActivity : BaseComponentActivity<LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()
    private var authenticationHandler: AuthenticationHandler? = null

    @Composable
    override fun ProvideCompose() {

        val activity: FragmentActivity = this

        var txtAccountNo by remember { mutableStateOf("") }
        var prefHelper = PreferenceHelper(this)
        authenticate(activity)
        val now = System.currentTimeMillis() // See note below
        val then: Long = prefHelper.isOtpSent
        val diffInMin: Long = TimeUnit.MINUTES.toMinutes(now - then)
        val seconds: Long = diffInMin / 1000
        val minutes = seconds / 60
        if (minutes <= 10) {
            val intent = Intent(this, OTPActivity::class.java)
            intent.putExtra("mobileNo", prefHelper.mobileno)
            startActivity(intent)
        }


        viewModel.loginResponse.observe(this,
            androidx.lifecycle.Observer {
                try {
//                    val obj = JSONObject(it)
//                    prefHelper.salt = obj.getString("salt")
//                    val now = System.currentTimeMillis() // See note below
//                    prefHelper.isOtpSent = now
                    val intent = Intent(this, OTPActivity::class.java)
                    intent.putExtra("mobileNo", txtAccountNo.trim())
                    startActivity(intent)
//                    showToast(this, obj.getString("meesage"))
                } catch (tx: Throwable) {
                    Log.e(
                        "My App",
                        "Could not parse malformed JSON: "
                    )
                }

            })

        LoginCompose {

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
                                HeaderView(SIGN_IN_JETPACK, ENTER_PHONE_NUMBER_OTP)
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
                                        .fillMaxSize()
                                        .padding(30.dp)
                                ) {

                                    Text(
                                        text = login,
                                        style = MaterialTheme.typography.subtitle1.copy(color = Color.Black),
                                        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
                                        fontFamily = mediumPoppFamily,
                                        fontSize = 14.sp
                                    )

                                    CustomStyleTextField(txtAccountNo) { txtAccountNo = it }

                                    RegistrationButton {
                                        if (NetworkUtils.isNetworkAvailable(this@LoginActivity)) {
                                            if (txtAccountNo.isEmpty()) {
                                                showToast(this@LoginActivity, ENTER_MOBILE_NUMBER)
                                            } else if (txtAccountNo.length <= 9) {
                                                showToast(
                                                    this@LoginActivity,
                                                    ENTER_VALIDE_MOBILE_NUMBER
                                                )
                                            } else {
                                                prefHelper.mobileno = txtAccountNo.trim()
                                                viewModel.onSignInBtnClick(
                                                    this@LoginActivity,
                                                    txtAccountNo.trim(),
                                                    ""
                                                )
                                            }
                                        } else {
                                            showToast(this@LoginActivity, NO_INTERNET_CONNECTION)

                                        }

                                    }

                                }

                            }

                        }
                    }
                }
            }
        }
    }

    private abstract class AuthenticationHandler {
        abstract fun onSuccess(): Intent?
    }

    private fun authenticate(context: FragmentActivity) {

        if (intent.getBooleanExtra(getString(R.string.preference_authenticate_only), false)) {
            Authentication.getInstance().auth(context, Callback())
        }

    }

    fun handleSuccess() {
        val a = authenticationHandler!!.onSuccess()
        if (a != null) {
            this.setResult(RESULT_OK, a)
        }
        finish()
    }

    inner class Callback : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            handleSuccess()
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
        }
    }

    @Composable
    fun CustomStyleTextField(
        txtAccountNo: String,
        setAcc: (String) -> Unit = {}
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            value = txtAccountNo,
            onValueChange = {
                if (it.length <= 10)
                    setAcc(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "+91") },
            leadingIcon = {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Image(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .size(25.dp),
                            bitmap = ImageBitmap.imageResource(id = R.drawable.india_flag),  // material icon
                            contentDescription = "custom_text_field"
                        )
                        Image(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .size(10.dp),
                            painter = painterResource(R.drawable.drop_down),
                            contentDescription = "custom_text_field"
                        )
                        Canvas(
                            modifier = Modifier.height(24.dp)
                        ) {
                            // Allows you to draw a line between two points (p1 & p2) on the canvas.
                            drawLine(
                                color = Color.LightGray,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 2.0F
                            )
                        }
                    }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = sign_phone_back_color,
                cursorColor = Color.Black,
                disabledLabelColor = sign_phone_back_color,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = placeholderColor
            ),
            shape = RoundedCornerShape(50.dp),
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
        )

    }

    companion object {
        fun getIntentForAuthentication(context: Context): Intent? {
            return getIntentForAuthentication(context, null)
        }

        fun getIntentForAuthentication(context: Context, destActivity: Intent?): Intent? {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra(context.getString(R.string.preference_authenticate_only), true)
            if (destActivity != null) intent.putExtra(
                context.getString(R.string.authentication_destination),
                destActivity
            )
            return intent
        }
    }

    @Composable
    fun LoginCompose(childrenCompose: @Composable () -> Unit) {
        childrenCompose()
    }

    @Composable
    private fun RegistrationButton(onClick: () -> Unit = {}) {

        Button(
            onClick = { onClick() },
            modifier = Modifier
                .padding(top = 10.dp, bottom = 34.dp)
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

    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        JetPackTheme {
            LoginCompose {
                CustomStyleTextField("")
                RegistrationButton()
            }

        }
    }
}


