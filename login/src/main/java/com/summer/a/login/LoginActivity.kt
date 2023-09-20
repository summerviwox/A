package com.summer.a.login

import android.os.Bundle
import com.summer.a.lib.activity.BaseActivity
import com.summer.a.login.databinding.LoginActivityBinding
import com.summer.a.provider.RouteAnno

@RouteAnno("LoginActivity")
class LoginActivity: BaseActivity() {

    private val loginActivityBinding by lazy {
        LoginActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(loginActivityBinding.root)
    }
}