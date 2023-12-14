package com.summer.a.album.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.summer.a.screenmatch.R
import com.summer.a.album.databinding.MonthItemBinding
import com.summer.a.album.databinding.YearItemBinding
import com.summer.a.album.databinding.YearViewBinding

/**
 * 很多月份列表预览view
 */
class MonthsView @JvmOverloads constructor(
    context:Context, attrs:AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        orientation = VERTICAL
        setPadding(
            resources.getDimension(R.dimen.sw_10dp).toInt(),
            0,
            resources.getDimension(R.dimen.sw_10dp).toInt(),
            0
        )
    }

    /**
     * 根据数据动态加载views
     */
    fun setData() {
        removeAllViews()
        var yearItemWith = resources.getDimension(R.dimen.sw_50dp)
        //去掉padding
        var innnerWidth = width - 2*resources.getDimension(R.dimen.sw_10dp)
        for (x in 17 .. 23) {
            var yearViewBinding = YearViewBinding.inflate(LayoutInflater.from(context), this, true)
            var yearItemBinding = YearItemBinding.inflate(LayoutInflater.from(context), yearViewBinding.root, true).run {
                root.text = x.toString()
            }
            for (y in 1..12) {
                MonthItemBinding.inflate(LayoutInflater.from(context), yearViewBinding.root, true).run {
                    root.text = y.toString()
                    root.layoutParams = LayoutParams(((innnerWidth - yearItemWith) / 12).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
                }
            }
        }
    }
}