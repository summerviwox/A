package com.summer.a.app

import android.app.Application
import com.summer.a.lib.Libinit

class AApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //ProviderManager.getProvider(RouterProvider::class.java)?.init(this)
        Libinit(this)
    }
}