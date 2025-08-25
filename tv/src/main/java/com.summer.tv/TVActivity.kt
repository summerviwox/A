package com.summer.tv

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.summer.a.view.activity.BaseUIActivity
import com.summer.a.tv.databinding.TvActivityBinding

@Route(path = "/tv/main")
class TVActivity:BaseUIActivity() {

    val binding by lazy { TvActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}