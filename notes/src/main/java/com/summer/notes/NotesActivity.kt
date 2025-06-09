package com.summer.notes

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.summer.notes.databinding.NotesActivityBinding
import com.summer.view.BaseUIActivity

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