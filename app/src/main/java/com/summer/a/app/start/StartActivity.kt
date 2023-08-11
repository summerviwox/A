package com.summer.a.app.start

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.summer.a.lib.activity.BaseActivity
import com.summer.a.lib.provider.RouterProvider

class StartActivity: BaseActivity() {

    private val startView by lazy {
        StartView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(startView)
        //ProviderManager.getProvider(RouterProvider::class.java)?.intent("/test/MainActivity")
        //StartModel().jump(this,MainActivity::class.java)
        var aa = AA("")

    }
}