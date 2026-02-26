package com.summer.module

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.summer.a.view.activity.BaseUIActivity

@Route(path = "/module/main")
class ModuleMainActivity: BaseUIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainView = MainView(this)
        setContentView(mainView)
        mainView.init()


    }
}