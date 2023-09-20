package com.summer.a.lib.provider

import android.content.Context

interface LoginProvider {

    fun login(context: Context,name:String,code:String);
}