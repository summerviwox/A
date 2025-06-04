package com.summer.notes

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.inputmethod.InputMethodManager
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.summer.notes.databinding.NotesActivityBinding
import com.summer.view.activity.BaseUIActivity

/**
 * activity-代办笔记
 */
class NotesActivity: BaseUIActivity() {

    val binding by lazy { NotesActivityBinding.inflate(layoutInflater,null,false) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ImmersionBar
            .with(this)
            .fitsSystemWindows(true)
            .statusBarDarkFont(true)
            .keyboardEnable(true)
            .init()
    }
}