package com.example.jetpack.ui.base


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.jetpack.theme.JetPackTheme
import com.example.jetpack.utils.showToast

abstract class BaseComponentActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: VM

    //override in child class if you don't want to use global loading state
    open val wantToShowCustomLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackTheme {
                ProvideCompose()
                SetUpLoadingDialog()
                SetUpErrorDialog()
            }
        }


    }

    @Composable
    private fun SetUpLoadingDialog() {
        if (!wantToShowCustomLoading) {
            val loadingValue = viewModel.showDialogLoadingPrivate.observeAsState()

            if (loadingValue.value == true) {
                ShowLoading()
            }
        }

    }

    @Composable
    private fun SetUpErrorDialog() {
        val vmLoadinState = viewModel.showMessageDialog.observeAsState()
        if (vmLoadinState.value != null){
            showToast(this, vmLoadinState.value!!)
        }
    }


    @Composable
    protected open fun ShowLoading() {

        Dialog(
            onDismissRequest = { viewModel.hideLoading() },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }


    @Composable
    private fun ShowErrorDialog(errorDescription: String) {

        AlertDialog(
            onDismissRequest = {
            },

            title = {
                Text("error", style = MaterialTheme.typography.h4)
            },
            text = {
                Text(errorDescription, fontSize = 16.sp)
            },
            confirmButton = {
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.hideMessageDialog("")
                    }) {
                    Text(
                        "Ok",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.White
        )

    }


    @Composable
    abstract fun ProvideCompose()

    @Composable
    abstract fun ProvideComposeLightPreview()


    inline fun <reified T : ComponentActivity> Context.startActivity(block: Intent.() -> Unit = {}) {

        startActivity(Intent(this, T::class.java).apply(block))
    }


}