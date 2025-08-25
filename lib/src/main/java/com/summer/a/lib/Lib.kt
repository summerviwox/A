package com.summer.a.lib

import android.app.Application
import com.alibaba.android.arouter.facade.annotation.Route
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

fun <T> navigation(service:Class<out T>):T{
    return ARouter.getInstance().navigation(service)
}