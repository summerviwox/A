package com.summer.module

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.summer.a.view.life.LifeRelativeLayout
import com.summer.module.databinding.BViewBinding
/**
 * UI b
 */
class BView(context: Context, attrs: AttributeSet? = null) : MyLifeConstraintLayout(context, attrs) {

    private val binding by lazy { BViewBinding.inflate(LayoutInflater.from(context),this) }

    init {
        binding.root
    }

    fun init(){
        binding.button.setOnClickListener {
            FindViewModel<ViewModel1>()?.loading?.postValue(true)
        }
        FindViewModel<ViewModel2>()?.data?.observe(this){
            binding.button.text = it.toString()
        }
    }
}