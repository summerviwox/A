package com.summer.module

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.summer.module.databinding.AViewBinding

/**
 * UI a
 */
class AView(context: Context, attrs: AttributeSet? = null) : MyLifeConstraintLayout(context, attrs) {

    private val binding by lazy { AViewBinding.inflate(LayoutInflater.from(context),this) }

    init {
        binding.root
    }

    fun init(){

        FindViewModel<ViewModel1>()?.loading?.observe(this){
            binding.loading.visibility = if(it) View.VISIBLE else View.GONE
            FindViewModel<ViewModel2>()?.initData()
        }
    }
}