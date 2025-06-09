package com.summer.lib.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class BaseViewModel:ViewModel() {

    companion object {
        inline fun <reified T : BaseViewModel> getWith(context: Context): T {
            return ViewModelProvider(context as BaseActivity)[T::class.java]
        }
    }
}