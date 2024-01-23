package com.example.jetpack.ui.activity.otp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OTPViewModel() : BaseViewModel() {

    private val otpResponsePrivate = MutableLiveData<String>()
    val otpResponse: LiveData<String> get() = otpResponsePrivate

    private val otpResendResponsePrivate = MutableLiveData<String>()
    val otpResendResponse: LiveData<String> get() = otpResendResponsePrivate

    fun onOtpBtnClick(context: Context, mobileno: String, otp: String, randomKey: String) {

        showLoading()

        viewModelScope.launch(Dispatchers.IO) {

//            val respondMap: Map<String, Any> = mapOf("mobileno" to mobileno, "otp" to otp)
//
//            var mapper = ObjectMapper()
//            val jsonrespondMapexit = mapper.writeValueAsString(respondMap)
//
//            val encrypted = NetworkEncryptAndDecrypt.encryptPayload(
//                jsonrespondMapexit.toString(),
//                SERVER_NETWORK_KEY
//            )
//
//            val data = CommonRequest(encrypted)

            otpResponsePrivate.postValue("")
            //API CALL
//            ApiClient.init(context)
//            val responseService = ApiClient.retrofit.create(Service::class.java).otp(data)
//            if (responseService.isSuccessful) {
//                hideLoading()
//                responseService.body()?.let {
//                    if (it.status.equals("success")) {
//                        val decrypted = NetworkEncryptAndDecrypt.decryptPayload(
//                            it.payload,
//                            SERVER_NETWORK_KEY
//                        )
//                        if (!decrypted.equals("error", true)) {
//                            otpResponsePrivate.postValue(decrypted)
//
//                        } else {
//                            showMessageDialog("Decryption error")
//                        }
//                    } else {
//                        showMessageDialog(responseService.message())
//                    }
//                }
//            } else {
//                responseService.errorBody()?.let { error ->
//                    showLoading()
//                    hideLoading()
//                    showMessageDialog("Response body is null")
//
//                }
//            }

        }
    }

    fun onResendOtpBtnClick(context: Context, mobileno: String, randomKey: String) {

        showLoading()

        viewModelScope.launch(Dispatchers.IO) {

//            val respondMap: Map<String, Any> = mapOf("mobileno" to mobileno)
//
//            var mapper = ObjectMapper()
//            val jsonrespondMapexit = mapper.writeValueAsString(respondMap)
//
//            val encrypted = NetworkEncryptAndDecrypt.encryptPayload(
//                jsonrespondMapexit.toString(),
//                "ATeqIgsGp82dZHt6Xb68ocCTEMNoSVy8"
//            )
//
//            val data = CommonRequest(encrypted)

            otpResendResponsePrivate.postValue("")

            //API CALL
//            ApiClient.init(context)
//            val responseService = ApiClient.retrofit.create(Service::class.java).resendKey(data)
//            if (responseService.isSuccessful) {
//                hideLoading()
//                responseService.body()?.let {
//                    if (it.status.equals("success")) {
//                        val decrypted = NetworkEncryptAndDecrypt.decryptPayload(
//                            it.payload,
//                            "ATeqIgsGp82dZHt6Xb68ocCTEMNoSVy8"
//                        )
//
//                        if (!decrypted.equals("error", true)) {
//                            otpResendResponsePrivate.postValue(decrypted)
//
//                        } else {
//                            showMessageDialog("Decryption error")
//                        }
//                    } else {
//                        showMessageDialog(responseService.message())
//                    }
//                }
//            } else {
//                responseService.errorBody()?.let { error ->
//                    showLoading()
//                    hideLoading()
//                    showMessageDialog("Response body is null")
//
//                }
//            }

        }
    }

}
