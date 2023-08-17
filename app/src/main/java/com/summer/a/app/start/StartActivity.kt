package com.summer.a.app.start

import android.os.Bundle
import com.summer.a.lib.activity.BaseActivity
import com.summer.a.lib.provider.ProviderManager
import com.summer.a.lib.provider.RouterProvider

class StartActivity: BaseActivity() {

    private val startView by lazy {
        StartView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(startView)
        ProviderManager.getProvider(RouterProvider::class.java)?.intent("/test/MainActivity")
    }
}