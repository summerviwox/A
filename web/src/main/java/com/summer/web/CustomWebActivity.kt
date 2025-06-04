package com.summer.web

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.summer.provider.RouteAnno
import com.summer.web.databinding.CustomwebActivityBinding

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