package com.summer.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.summer.a.tv.R
import com.summer.a.tv.databinding.APresenterBinding
import com.summer.a.tv.databinding.CPresenterBinding
import com.summer.a.tv.databinding.LayoutAdditionBinding
import kotlin.random.Random

class CPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = CPresenterBinding.inflate(LayoutInflater.from(parent?.context))
        //binding.a.layoutParams.height = Random.nextInt(50)+50
        //binding.a.requestLayout()

        binding.root.setOnFocusChangeListener { view, b ->
            FocusViewScaleUtil.setViewAnimator(view,b,0.9f,1f)
        }

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        viewHolder?.let {
            val binding = CPresenterBinding.bind(it.view)
            it.view.postDelayed({
                binding.addtion.addView(LayoutAdditionBinding.inflate(LayoutInflater.from(it.view.context)).root)
            },5000)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}