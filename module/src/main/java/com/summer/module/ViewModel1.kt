package com.summer.module

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 业务数据1
 */
class ViewModel1:ViewModel() {

    val loading = MutableLiveData(false)

}