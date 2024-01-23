package com.example.jetpack.data.network


import com.example.jetpack.data.network.requestModel.CommonRequest
import com.example.jetpack.data.network.responceModel.CommonResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface Service {

    @POST("/")
    suspend fun login(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun otp(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun resendKey(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun userauth(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun getCredentials(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun addcredentials(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun editcredentials(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun deletecredentials(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun getuserdirectory(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun saveuserdirectory(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun postSaveShareCredtemp(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun postSaveShareCreduser(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun postGetShareCreduser(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun postGetShareDetailsForCred(@Body request: CommonRequest): Response<CommonResponse>

    @POST("/")
    suspend fun postDeleteShareCred(@Body request: CommonRequest): Response<CommonResponse>
}
