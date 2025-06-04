package com.summer.lib.provider

import android.content.Context

interface LoginProvider {

    fun login(context: Context, name: String, code: String);
}