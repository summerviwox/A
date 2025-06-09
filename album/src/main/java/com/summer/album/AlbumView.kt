package com.summer.module.album

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.cmccit.basemodule.customview.lifecycle.LifecycleFrameLayout
import com.summer.album.FoldersView
import com.summer.album.MonthAlbumView
import com.summer.album.databinding.AlbumActivityBinding
import com.summer.lib.activity.BaseViewModel

/**
 * 相册view
 */
class AlbumView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LifecycleFrameLayout(context, attrs) {

    val mBinding: AlbumActivityBinding by lazy {AlbumActivityBinding.inflate(LayoutInflater.from(context), this)  }
    val monthAlbumView by lazy { MonthAlbumView(context) }
    val foldersView by lazy { FoldersView(context) }
    init {
        val views = arrayListOf(monthAlbumView,foldersView)
        mBinding.viewpager.adapter = object : PagerAdapter(){
            override fun getCount(): Int {
                return 2
            }
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view==`object`
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                container.addView(views[position])
                return views[position]
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeViewAt(position)
            }
        }
        BaseViewModel.getWith<AlbumViewModel>(context).rootFolder.observe(this){
            foldersView.setData(it)
        }
        BaseViewModel.getWith<AlbumViewModel>(context).items.observe(this){
            monthAlbumView.setData(it)
        }
    }

}