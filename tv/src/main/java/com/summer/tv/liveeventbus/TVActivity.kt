package com.summer.tv.liveeventbus

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jeremyliao.liveeventbus.LiveEventBus
import com.summer.a.tv.databinding.LayoutBBinding
import com.summer.a.view.activity.BaseUIActivity

@Route(path = "/tv/liveeventbus")
class LiveEventBusActivity: BaseUIActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(TVView(this))

        var binding = LayoutBBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.test.setOnClickListener {
            LiveEventBus.get<String>("12").post(System.currentTimeMillis().toString())
        }
    }
}