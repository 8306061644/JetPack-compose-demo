package com.example.jetpack.ui.activity.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel() : BaseViewModel() {


    private val loginResponsePrivate = MutableLiveData<String>()
    val loginResponse: LiveData<String> get() = loginResponsePrivate

    fun onSignInBtnClick(context: Context, mobileNumber: String, randomKey: String) {

        showLoading()

        viewModelScope.launch(Dispatchers.IO) {

//            val respondMap: Map<String, Any> = mapOf("mobileno" to "91$mobileNumber")
//
//            var mapper = ObjectMapper()
//            val jsonrespondMapexit = mapper.writeValueAsString(respondMap)

//            val encrypted = NetworkEncryptAndDecrypt.encryptPayload(
//                jsonrespondMapexit.toString(),
//                SERVER_NETWORK_KEY
//            )

//            val data = CommonRequest(encrypted)

            loginResponsePrivate.postValue("")

            //API CAll
//            ApiClient.init(context)
//            val responseService = ApiClient.retrofit.create(Service::class.java).login(data)
//            if (responseService.isSuccessful) {
//
//                responseService.body()?.let {
//                    if (it.status.equals("success")) {
//                        val decrypted = NetworkEncryptAndDecrypt.decryptPayload(
//                            it.payload,
//                            SERVER_NETWORK_KEY
//                        )
//                        if (!decrypted.equals("error", true)) {
//                            loginResponsePrivate.postValue(decrypted)
//                            hideLoading()
//                        } else {
//                            showMessageDialog("Decryption error")
//                        }
//                    } else {
//                        hideLoading()
//                        showMessageDialog(it.message)
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
