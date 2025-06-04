package com.summer.login

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.summer.lib.provider.LoginProvider
import com.summer.provider.ProviderAnno
import com.summer.provider.RouterProvider

@ProviderAnno(LoginProvider::class)
class LoginImp : LoginProvider {

    override fun login(context: Context, name: String, code: String) {
        LogUtils.e(name, code)
        try {
            var clazz = Class.forName(RouterProvider.get(name))
            context.startActivity(Intent(context, clazz))
        } catch (e: Exception) {
        }
    }
}