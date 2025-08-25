package com.summer.a.lib

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

fun Libinit(application: Application){
    ARouter.init(application)
    ARouter.openLog()
    ARouter.openDebug()
    ARouter.printStackTrace()
}

fun goTo(path:String){
    ARouter.getInstance().build(path).navigation()
}