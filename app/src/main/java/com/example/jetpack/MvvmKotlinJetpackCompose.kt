package com.example.jetpack

import android.app.Application

class MvvmKotlinJetpackCompose : Application() {


    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    fun getContext(): MvvmKotlinJetpackCompose? {
        return mContext
    }
    companion object{
        private var mContext: MvvmKotlinJetpackCompose? = null


    }
}