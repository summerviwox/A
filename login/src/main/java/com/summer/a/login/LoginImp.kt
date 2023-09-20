package com.summer.a.login

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.google.auto.service.AutoService
import com.summer.a.lib.provider.LoginProvider
import com.summer.a.provider.ProviderAnno
import com.summer.a.router.RouterProvider

@ProviderAnno(LoginProvider::class)
class LoginImp:LoginProvider {

    override fun login(context: Context,name: String, code: String) {
        LogUtils.e(name,code)
        context.startActivity(Intent(context,RouterProvider.getClass(name)?.java))
    }
}