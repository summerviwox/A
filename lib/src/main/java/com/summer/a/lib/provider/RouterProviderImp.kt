package com.summer.a.lib.provider

import com.alibaba.android.arouter.launcher.ARouter
import com.google.auto.service.AutoService
import com.summer.a.provider.ProviderAnno

@ProviderAnno(RouterProvider::class)
class RouterProviderImp:RouterProvider {

    override fun intent(path: String) {
        ARouter.getInstance().build(path).navigation()
    }
}