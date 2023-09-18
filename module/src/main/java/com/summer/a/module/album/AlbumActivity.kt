package com.summer.a.module.album

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.summer.a.lib.activity.BaseActivity

@Route(path = "/Album/Album")
class AlbumActivity:BaseActivity(){

    private val albumView by lazy {
        AlbumView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(albumView)

    }
}