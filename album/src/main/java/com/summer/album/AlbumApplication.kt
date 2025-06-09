package com.summer.album

import android.app.Application
import com.summer.database.album.ItemRoomDatabase

open class AlbumApplication:Application() {
    companion object{
        private lateinit var application:AlbumApplication
        fun get():AlbumApplication{
            return application;
        }
        val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(application) }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}