package com.summer.album

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cmccit.basemodule.customview.lifecycle.LifecycleRecyclerView
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.summer.album.databinding.AlbumAdapterBinding
import com.summer.database.album.Item
import com.summer.lib.activity.BaseActivity
import com.summer.lib.activity.BaseViewModel
import com.summer.lib.activity.getViewModel
import com.summer.module.album.AlbumActivity
import com.summer.module.album.AlbumViewModel
import java.io.File
import java.util.Calendar

class MonthAlbumView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LifecycleRecyclerView(context, attrs) {

    val requestOptions = RequestOptions().also { it.encodeQuality(10) }
    var items = mutableListOf<Item>()
    lateinit var albumDecoration:AlbumDecoration
    init {

        val spanWidth = ScreenUtils.getScreenWidth()/5
        class MyViewHolder(itemView: View) : ViewHolder(itemView) {
            fun setData(item:Item,position:Int){
                var view = AlbumAdapterBinding.bind(itemView)
                view.root.layoutParams.height = spanWidth
                view.videotype.visibility = if(item.atype.equals("video")) View.VISIBLE else View.GONE
                view.videotype.layoutParams.width = spanWidth/3
                view.videotype.layoutParams.height = spanWidth/3
                loadImage(view.image,item,position)
            }
        }
        val myAdapter = object : Adapter<MyViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                return MyViewHolder(AlbumAdapterBinding.inflate(LayoutInflater.from(context),parent,false).root)
            }

            override fun getItemCount(): Int {
                return items.size
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.setData(items[position],position)
            }
        }
        adapter = myAdapter
//        (context as BaseActivity).getViewModel<AlbumViewModel>().items.observe(this){
//            items = it
//            LogUtils.e(items.size)//25424
//            albumDecoration = AlbumDecoration(context,items)
//            addItemDecoration(albumDecoration)
//            myAdapter.notifyDataSetChanged()
//            layoutManager?.scrollToPosition(items.size-1)
//        }
        BaseViewModel.getWith<AlbumViewModel>(context).positionOfMonthAlbum.observe(this){
            layoutManager?.scrollToPosition(it)
        }
        layoutManager = GridLayoutManager(context,5).also {
            it.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return items[position].empty
                }

            }
        }
    }
    fun loadImage(imageView: ImageView,item:Item,position:Int){
        //E:\\records\\20221204\\1670118503453.jpg
        var localFile = item.locpath?.let { File(it) }
        if(localFile?.exists()==true){
            Glide.with(context).asBitmap().apply(requestOptions).load(localFile.path).into(imageView)
        }else{
            Glide.with(context).asBitmap().apply(requestOptions).load(AlbumUrlTool.getImageThumbnailUrl(item.netpath,item)).into(imageView)
        }
        imageView.setOnClickListener {
            val albumsView = AlbumsView(context)
            BaseViewModel.getWith<AlbumViewModel>(context).items.value?.let {
                albumsView.setData(it,position)
                (context as AlbumActivity).albumView.addView(albumsView, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
            }

        }
    }

    fun setData(items:MutableList<Item>){
        this.items = items
        //LogUtils.e(items.size)//25424
        albumDecoration = AlbumDecoration(context,items)
        addItemDecoration(albumDecoration)
        adapter?.notifyDataSetChanged()
        layoutManager?.scrollToPosition(items.size-1)
    }
}