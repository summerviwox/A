package com.summer.tv

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class TVViewModel: ViewModel() {

    companion object{
        fun get(context: Context):TVViewModel?{
            if(context is ViewModelStoreOwner){
                return ViewModelProvider(context)[TVViewModel::class.java]
            }
            return null
        }
    }

    val life = MutableLiveData<Int>()
}