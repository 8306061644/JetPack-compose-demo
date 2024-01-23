package com.example.jetpack.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {

    val showDialogLoadingPrivate = MutableLiveData<Boolean>(false)

    val showMessageDialog = MutableLiveData<String>()

    fun isLoading(): Boolean {
        return showDialogLoadingPrivate.value!!
    }

    fun showLoading() {
        if (!showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.postValue(true)
        }

    }

    fun hideLoading() {
        if (showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.postValue(false)
        }
    }

    fun showMessageDialog(dataError: String) {
        showMessageDialog.postValue(dataError)
    }

    fun hideMessageDialog(success: String) {
        showMessageDialog.postValue(success)

    }

}

