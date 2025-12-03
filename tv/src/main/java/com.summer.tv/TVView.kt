package com.summer.tv

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroupOverlay
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintProperties
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ClassPresenterSelector
import androidx.leanback.widget.ItemBridgeAdapter
import androidx.leanback.widget.OnChildViewHolderSelectedListener
import androidx.leanback.widget.VerticalGridView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.blankj.utilcode.util.LogUtils
import com.summer.a.tv.R
import com.summer.a.tv.databinding.TextBinding
import com.summer.a.tv.databinding.TvViewBinding
import com.summer.a.view.life.LifeConstraintLayout
import com.summer.a.view.life.LifeLinearLayout
import kotlin.random.Random
import com.summer.a.view.R as RR

class TVView(context: Context, attrs: AttributeSet? = null) : LifeConstraintLayout(context, attrs) {

    val binding by lazy { TvViewBinding.inflate(LayoutInflater.from(context),this,true) }
    init {
        TVViewModel.get(context)?.life?.observe(this){
            LogUtils.e("TVView",it)
        }
        val arrayObjectAdapter:ArrayObjectAdapter

        //binding.gridview.itemAlignmentOffsetPercent = 0f


        binding.gridview.let {
//            it.windowAlignment
//            it.windowAlignmentOffset
//            it.windowAlignmentOffsetPercent
//
//            it.itemAlignmentOffset
//            it.itemAlignmentOffsetPercent
            it.setWindowAlignment(VerticalGridView.WINDOW_ALIGN_HIGH_EDGE);
            it.setItemAlignmentOffset(0);           // 项从顶部开始对齐
            it.setItemAlignmentOffsetPercent(0f);   // 项的0%位置（顶部）
            it.setWindowAlignmentOffset(0);         // 容器从顶部开始
            it.setWindowAlignmentOffsetPercent(0f); // 容器的0%位置（顶部）
        }

        binding.gridview.adapter = ItemBridgeAdapter(ArrayObjectAdapter(ClassPresenterSelector()
            .addClassPresenter(AData::class.java,APresenter())
            .addClassPresenter(BData::class.java,BPresenter())
            .addClassPresenter(CData::class.java,CPresenter())
        ).also { arrayObjectAdapter = it })
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())
        arrayObjectAdapter.add(CData())

        var unFocusView: View? = null
//        var textView = TextView(context)
//        textView.text = "111111111111111111111111"
//        textView.setBackgroundColor(context.resources.getColor(com.summer.a.view.R.color.color_00ff00))
//        addView(textView)

        val text1 = TextBinding.inflate(LayoutInflater.from(context),null,false)

        binding.gridview.setOnChildSelectedListener { parent, view, position, id ->
//            text1.root.parent?.let {
//                (it as ViewGroup).removeView(text1.root)
//            }
//            (binding.gridview.getChildAt(binding.gridview.selectedPosition).overlay as ViewGroupOverlay).add(text1.root)


//            val topView = view?.findViewById<View>(R.id.top)
//            LogUtils.e(position)
//            unFocusView?.scaleY = 1f
//            topView?.scaleY = 1.4f
//            unFocusView = topView
//            val r = Random.nextInt(200)
//            LogUtils.e(r)
//            ConstraintProperties(textView).connect(
//                ConstraintProperties.TOP,
//                ConstraintProperties.PARENT_ID,
//                ConstraintProperties.TOP,r)
//            textView.requestLayout()

//            var scroll = object: OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    val r = Random.nextInt(200)
//                    LogUtils.e(r)
//                    ConstraintProperties(textView).connect(
//                        ConstraintProperties.TOP,
//                        ConstraintProperties.PARENT_ID,
//                        ConstraintProperties.TOP,r)
//                    textView.requestLayout()
//                }
//            }

//            binding.gridview.addOnScrollListener(scroll)
//            scroll.onScrolled(binding.gridview,0,0)
        }

        binding.gridview.setOnChildViewHolderSelectedListener(object:
            OnChildViewHolderSelectedListener(){
            override fun onChildViewHolderSelectedAndPositioned(
                parent: RecyclerView,
                child: RecyclerView.ViewHolder?,
                position: Int,
                subposition: Int
            ) {
                super.onChildViewHolderSelectedAndPositioned(parent, child, position, subposition)
                LogUtils.e(111111)
            }

            override fun onChildViewHolderSelected(
                parent: RecyclerView,
                child: RecyclerView.ViewHolder?,
                position: Int,
                subposition: Int
            ) {
                super.onChildViewHolderSelected(parent, child, position, subposition)
                child?.let {
                    //binding.gridview.scrollToPosition(position)
                    LogUtils.e(position)
                    //scrollTo(0,child.itemView.y.toInt())
                }
            }
        })
    }


}