package com.summer.module

import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

//fun View.requireActivity(): ComponentActivity {
//    var ctx = context
//    while (ctx is ContextWrapper) {
//        if (ctx is ComponentActivity) return ctx
//        ctx = ctx.baseContext
//    }
//    error("View not attached to Activity")
//}

fun Context.FindActivity(): ComponentActivity?{
    if(this is ComponentActivity){
        return this
    }
    if(this is ContextWrapper){
        return this.baseContext.FindActivity()
    }
    return null
}

inline fun <reified T : ViewModel> View.FindViewModel(): T? {
    var activity: ComponentActivity = context.FindActivity() ?: return null
    return ViewModelProvider(activity)[T::class.java]
}

