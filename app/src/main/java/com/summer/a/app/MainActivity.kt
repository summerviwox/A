package com.summer.a.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.summer.a.app.databinding.MainActivityBinding
import com.summer.a.lib.provider.LoginProvider

@Route(path = "/test/MainActivity")
class MainActivity:FragmentActivity() {

    private val mainActivityBinding: MainActivityBinding by lazy {
        MainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)
        mainActivityBinding.c.setOnClickListener {
            //ProviderManager.getProvider(LoginProvider::class.java)?.login("name","code")
        }
        //startActivity(Intent(this,LoginActivity::class.java))
    }


}