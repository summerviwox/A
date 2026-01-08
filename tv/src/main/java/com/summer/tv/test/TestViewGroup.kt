package com.summer.tv.test

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils

class TestViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        LogUtils.e("TestViewGroup dispatchKeyEvent ${event?.action}")
        return super.dispatchKeyEvent(event)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("TestViewGroup dispatchTouchEvent ${event?.action}")
        return super.dispatchTouchEvent(event)
    }
}