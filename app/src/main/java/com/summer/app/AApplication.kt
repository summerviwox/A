package com.summer.app

import android.app.Application
import com.summer.album.AlbumApplication

class AApplication : AlbumApplication() {

    override fun onCreate() {
        super.onCreate()
        //ProviderManager.getProvider(RouterProvider::class.java)?.init(this)
    }
}