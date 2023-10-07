package com.summer.a.app

import android.app.Application

class AApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //ProviderManager.getProvider(RouterProvider::class.java)?.init(this)
    }
}