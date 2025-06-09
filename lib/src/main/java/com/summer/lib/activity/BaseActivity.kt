package com.summer.lib.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

public open class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
inline fun <reified T : BaseViewModel> BaseActivity.getViewModel(): T {
    return BaseViewModel.getWith<T>(this)
}