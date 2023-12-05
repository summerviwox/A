package com.summer.a.module.album

import android.os.Bundle
import com.summer.a.lib.activity.BaseActivity
import com.summer.a.provider.RouteAnno
import com.summer.a.view.activity.BaseUIActivity

@RouteAnno("AlbumActivity")
class AlbumActivity : BaseUIActivity() {

    private val albumView by lazy {
        AlbumView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(albumView)

    }
}