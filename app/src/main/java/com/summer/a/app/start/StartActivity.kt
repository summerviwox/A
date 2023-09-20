package com.summer.a.app.start

import android.content.Intent
import android.os.Bundle
import com.summer.a.app.MainActivity
import com.summer.a.lib.activity.BaseActivity

class StartActivity: BaseActivity() {

    private val startView by lazy {
        StartView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(startView)
        startActivity(Intent(this,MainActivity::class.java))
        //ProviderManager.getProvider(RouterProvider::class.java)?.intent(RouterPath._test_MainActivity)
    }
}