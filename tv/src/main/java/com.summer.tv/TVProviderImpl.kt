package com.summer.tv

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.summer.a.lib.provider.TVProvider
@Route(path = "/tv/provider")
class TVProviderImpl:TVProvider {
    override fun show() {
        LogUtils.e("${javaClass.name} show")
    }

    override fun init(context: Context?) {
         LogUtils.e("${javaClass.name} init")
    }
}