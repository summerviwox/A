package com.summer.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.summer.a.tv.R
import com.summer.a.tv.databinding.APresenterBinding
import com.summer.a.tv.databinding.CPresenterBinding
import kotlin.random.Random

class CPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = CPresenterBinding.inflate(LayoutInflater.from(parent?.context))
        binding.a.layoutParams.height = Random.nextInt(50)+50
        binding.a.requestLayout()
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }
}