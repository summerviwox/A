package com.summer.a.video

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.summer.a.video.databinding.VideoActivityBinding

class VideoView @JvmOverloads constructor(
    context:Context, attrs:AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val videoActivityBinding by lazy {
        VideoActivityBinding.inflate(LayoutInflater.from(context),this,true)
    }

    init {
        videoActivityBinding.videoPlayer.run {
            setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",true,"")
            startPlayLogic()
        }
    }
}