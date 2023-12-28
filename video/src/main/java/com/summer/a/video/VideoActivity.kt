package com.summer.a.video

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class VideoActivity:FragmentActivity() {

    private val videoView by lazy {
        VideoView(this)
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(videoView)
    }

}