package com.summer.a.app

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class AApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();
        ARouter.init(this);
    }
}