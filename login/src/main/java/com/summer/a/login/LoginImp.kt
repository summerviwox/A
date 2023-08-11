package com.summer.a.login

import com.blankj.utilcode.util.LogUtils
import com.google.auto.service.AutoService
import com.summer.a.lib.provider.LoginProvider

@AutoService(LoginProvider::class)
class LoginImp:LoginProvider {

    override fun login(name: String, code: String) {
        LogUtils.e(name,code)
    }
}