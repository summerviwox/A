package com.summer.module.album

import android.os.Bundle
import com.summer.provider.RouteAnno
import com.summer.view.activity.BaseUIActivity

@RouteAnno("AlbumActivity")
class AlbumActivity : BaseUIActivity() {

    private val albumView by lazy {
        AlbumView(this)
    }

    private val albumViewModel by lazy {
        AlbumViewModel.instance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(albumView)
        albumViewModel.getData()
    }
}