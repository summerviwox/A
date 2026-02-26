package com.summer.module

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.summer.a.view.life.LifeConstraintLayout
import com.summer.module.databinding.ModuleMainActivityBinding

class MainView(context: Context, attrs: AttributeSet? = null) : LifeConstraintLayout(context, attrs) {

    private val binding by lazy { ModuleMainActivityBinding.inflate(LayoutInflater.from(context),this) }

    init {
        binding.root.setBackgroundResource(com.summer.a.view.R.color.color_ffffff)
    }

    fun init(){
        binding.aview.init()
        binding.bview.init()
    }
}