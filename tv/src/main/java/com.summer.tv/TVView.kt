package com.summer.tv

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.blankj.utilcode.util.LogUtils
import com.summer.a.tv.R
import com.summer.a.tv.databinding.TvViewBinding
import com.summer.a.view.life.LifeLinearLayout

class TVView(context: Context, attrs: AttributeSet? = null) : LifeLinearLayout(context, attrs) {

    val binding by lazy { TvViewBinding.inflate(LayoutInflater.from(context),this) }
    init {
        setBackgroundResource(com.summer.a.view.R.color.color_ffffff)
        TVViewModel.get(context)?.life?.observe(this){
            LogUtils.e("TVView",it)
        }
        var count = 0
        binding.text.setOnClickListener { TVViewModel.get(context)?.life?.postValue(count++) }
    }


}