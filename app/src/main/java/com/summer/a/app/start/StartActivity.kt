package com.summer.a.app.start

import android.os.Bundle
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jeremyliao.liveeventbus.core.Console
import com.summer.a.lib.goTo
import com.summer.a.lib.navigation
import com.summer.a.lib.provider.TVProvider
import com.summer.a.view.activity.BaseUIActivity

class StartActivity : BaseUIActivity() {

    private val startView by lazy {
        StartView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(startView)
        //startActivity(Intent(this, MainActivity::class.java))
        //ProviderManager.getProvider(RouterProvider::class.java)?.intent(RouterPath._test_MainActivity)
        //navigation(TVProvider::class.java)

        goTo( "/module/main")

        //LiveEventBus.get<String>("12").post("111111")

//        LiveEventBus.get<String>("12").observeSticky(this){
//            LogUtils.e(it)
//            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
//        }
    }
}