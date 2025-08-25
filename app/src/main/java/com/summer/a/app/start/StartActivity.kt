package com.summer.a.app.start

import android.os.Bundle
import com.summer.a.lib.goTo
import com.summer.a.view.activity.BaseUIActivity

class StartActivity : BaseUIActivity() {

    private val startView by lazy {
        StartView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(startView)
        //startActivity(Intent(this, MainActivity::class.java))
        //ProviderManager.getProvider(RouterProvider::class.java)?.intent(RouterPath._test_MainActivity)
        goTo("/tv/main")
    }
}