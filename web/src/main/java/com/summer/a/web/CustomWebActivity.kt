package com.summer.a.web

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.summer.a.provider.RouteAnno
import com.summer.a.web.databinding.CustomwebActivityBinding

@RouteAnno("CustomWebActivity")
class CustomWebActivity : FragmentActivity() {

    private val customwebActivityBinding: CustomwebActivityBinding by lazy {
        CustomwebActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(customwebActivityBinding.root)

    }
}