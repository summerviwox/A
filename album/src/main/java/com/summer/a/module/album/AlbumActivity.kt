package com.summer.a.module.album

import android.os.Bundle
import com.summer.a.lib.activity.BaseActivity
import com.summer.a.provider.RouteAnno
import com.summer.a.view.activity.BaseUIActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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