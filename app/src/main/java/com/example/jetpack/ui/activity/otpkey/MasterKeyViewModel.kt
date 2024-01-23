package com.example.jetpack.ui.activity.otpkey

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetpack.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MasterKeyViewModel() : BaseViewModel() {

    private val masterResponsePrivate = MutableLiveData<String>()
    val masterResponse: LiveData<String> get() = masterResponsePrivate

    fun onMasterBtnClick(context: Context, mobileno: String, pass: String, randomKey: String) {

        showLoading()

        viewModelScope.launch(Dispatchers.IO) {

//            val respondMap: Map<String, Any> =
//                mapOf("mobileno" to mobileno, "pass" to pass)
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

            masterResponsePrivate.postValue("")
            //API CALL
//            ApiClient.init(context)
//            val responseService = ApiClient.retrofit.create(Service::class.java).userauth(data)
//            if (responseService.isSuccessful) {
//                hideLoading()
//                responseService.body()?.let {
//                    if (it.status.equals("success")) {
//                        val decrypted = NetworkEncryptAndDecrypt.decryptPayload(
//                            it.payload,
//                            SERVER_NETWORK_KEY
//                        )
//
//                        if (!decrypted.equals("error", true)) {
//                            masterResponsePrivate.postValue(decrypted)
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
