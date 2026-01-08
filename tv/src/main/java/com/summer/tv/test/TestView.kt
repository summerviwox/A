package com.summer.tv.test

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils

class TestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        LogUtils.e("TestView dispatchKeyEvent ${event?.action}")
        return super.dispatchKeyEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("TestView dispatchTouchEvent ${event?.action}")
        return super.dispatchTouchEvent(event)
    }
}