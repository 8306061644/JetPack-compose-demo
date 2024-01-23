package com.example.jetpack.data.network

import android.content.Context
import com.example.jetpack.BuildConfig
import com.example.jetpack.helper.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    @Volatile
    lateinit var retrofit: Retrofit
    private lateinit var httpClientBuilder: OkHttpClient.Builder

    fun init(context: Context) {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        httpClientBuilder = OkHttpClient()
            .newBuilder()

        var token = ""
        var platformId = ""

        var prefs = PreferenceHelper(context);
        if (!prefs.token.equals("")) {
            token = prefs.token
        }
        platformId = prefs.plaformId

        httpClientBuilder.addInterceptor(
            Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader(
                        "Authorization",
                        token
                    )
                    .addHeader("platformid", platformId)
                    .addHeader("platformtype", "android")
                    .build()
                chain.proceed(request)
            }
        )


        //add token to header
        httpClientBuilder.readTimeout(100, TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(100, TimeUnit.SECONDS)


        val httpClient = httpClientBuilder
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()


    }


}