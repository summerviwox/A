package com.summer.a.app

import android.app.Application
import androidx.multidex.MultiDexApplication

class AApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        //ProviderManager.getProvider(RouterProvider::class.java)?.init(this)
    }
}