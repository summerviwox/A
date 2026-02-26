package com.summer.module

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 业务数据2
 */
class ViewModel2:ViewModel() {

    val data = MutableLiveData<Int>()

    fun initData(){
        viewModelScope.launch {
            delay(5000)
            data.value = 56
        }
    }
}