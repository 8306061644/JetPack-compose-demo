package com.example.jetpack.data.network.responceModel

import com.google.gson.annotations.SerializedName


data class CommonResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("payload")
    var payload: String,
    @SerializedName("message")
    var message: String,
)