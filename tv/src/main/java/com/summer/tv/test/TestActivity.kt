package com.summer.tv.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.summer.a.tv.R
import com.summer.a.tv.databinding.TestActivityBinding
import com.summer.a.view.activity.BaseUIActivity
@Route(path = "/tv/main")
class TestActivity: BaseUIActivity() {

    val binding by lazy { TestActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.testview.requestFocus()
        LogUtils.e(
                "KeyEvent.ACTION_DOWN:${KeyEvent.ACTION_DOWN}" +
                "KeyEvent.ACTION_UP:${KeyEvent.ACTION_UP}" +
                "MotionEvent.ACTION_DOWN:${MotionEvent.ACTION_DOWN}"+
                "MotionEvent.ACTION_MOVE:${MotionEvent.ACTION_MOVE}"+
                "MotionEvent.ACTION_UP:${MotionEvent.ACTION_UP}"
        )
    }

    @SuppressLint("RestrictedApi")
    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        LogUtils.e("TestActivity dispatchKeyEvent ${event?.action}")
        return super.dispatchKeyEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("TestActivity dispatchTouchEvent ${event?.action}")
        return super.dispatchTouchEvent(event)
    }
}