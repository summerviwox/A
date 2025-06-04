package com.summer.login

import android.os.Bundle
import com.summer.lib.activity.BaseActivity
import com.summer.login.databinding.LoginActivityBinding
import com.summer.provider.RouteAnno

@RouteAnno("LoginActivity")
class LoginActivity : BaseActivity() {

    private val loginActivityBinding by lazy {
        LoginActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(loginActivityBinding.root)
    }
}