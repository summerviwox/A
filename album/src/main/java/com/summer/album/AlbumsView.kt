package com.summer.album

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.cmccit.basemodule.customview.lifecycle.LifecycleFrameLayout
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.summer.album.databinding.AlbumsBinding
import com.summer.album.databinding.ImageBinding
import com.summer.database.album.Item
import com.summer.lib.activity.BaseActivity
import com.summer.lib.activity.BaseViewModel
import com.summer.module.album.AlbumViewModel
import java.io.File
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.shuyu.gsyvideoplayer.utils.GSYVideoType

class AlbumsView(context: Context, attrs: AttributeSet? = null) :
    LifecycleFrameLayout(context, attrs) {

    val binding by lazy { AlbumsBinding.inflate(LayoutInflater.from(context),this) }
    var items = mutableListOf<Item>()
    init {

        class MyViewHolder(itemView: View) : ViewHolder(itemView) {
            fun setData(item:Item,position:Int){
                var view = ImageBinding.bind(itemView)
                if(item.atype.equals("video")){
                    view.image.visibility = GONE
                    view.video.visibility = VISIBLE
                    loadVideo(view.video,item,position)
                }else{
                    view.image.visibility = VISIBLE
                    view.video.visibility = GONE
                    loadImage(view.image,item,position)
                }
            }

        }
        val myAdapter = object : RecyclerView.Adapter<MyViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                return MyViewHolder(ImageBinding.inflate(LayoutInflater.from(context),parent,false).root)
            }

            override fun getItemCount(): Int {
                return items.size
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.setData(items[position],position)
            }
        }
        binding.viewpager.adapter = myAdapter

        (context as BaseActivity).lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when(event){
                    Lifecycle.Event.ON_PAUSE->GSYVideoManager.onPause()
                    Lifecycle.Event.ON_RESUME->GSYVideoManager.onResume()
                    Lifecycle.Event.ON_DESTROY->GSYVideoManager.releaseAllVideos()
                    else->{}
                }
            }
        })
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                LogUtils.e(11)
                GSYVideoManager.releaseAllVideos()
                if(items[position].atype.equals("video")){
                    val holder = (binding.viewpager.getChildAt(0) as RecyclerView).findViewHolderForAdapterPosition(position)
                    if(holder!=null){
                        var view = ImageBinding.bind((holder as MyViewHolder).itemView)
                        view.video.startPlayLogic()

                    }
                }
            }
        })
    }

    fun setData(items:MutableList<Item>,position: Int){
        this.items = items
        binding.viewpager.adapter?.notifyDataSetChanged()
        binding.viewpager.setCurrentItem(position,false)
        binding.viewpager.adapter?.notifyItemChanged(position)
    }


    fun loadImage(imageView: ImageView, item:Item,position:Int){
        //E:\\records\\20221204\\1670118503453.jpg
        var localFile = item.locpath?.let { File(it) }
        if(localFile?.exists()==true){
            LogUtils.e(localFile.path)
            Glide.with(context).load(localFile.path).into(imageView)
        }else{
            Glide.with(context).load(AlbumUrlTool.getImageUrl(item.netpath))
                .thumbnail(Glide.with(context).load(AlbumUrlTool.getImageThumbnailUrl(item.netpath,item)))
                .into(imageView)
        }
        imageView.setOnClickListener {
            if(localFile?.exists()!=true){
                LogUtils.e(AlbumUrlTool.getImageUrl(item.netpath))
            }
            BaseViewModel.getWith<AlbumViewModel>(context).positionOfMonthAlbum.postValue(position)
            visibility = GONE
        }
    }
    fun loadVideo(video: StandardGSYVideoPlayer, item:Item, position: Int){
        var localFile = item.locpath?.let { File(it) }
        if(localFile?.exists()==true){
            video.setUp(localFile.path,true,null)
        }else{
            video.setUp(AlbumUrlTool.getImageUrl(item.netpath),true,null)
        }
        video.backButton.visibility = VISIBLE
        video.backButton.setOnClickListener {
            LogUtils.e(AlbumUrlTool.getImageUrl(item.netpath))
            BaseViewModel.getWith<AlbumViewModel>(context).positionOfMonthAlbum.postValue(position)
            video.setVideoAllCallBack(null)
            GSYVideoManager.releaseAllVideos();
            visibility = GONE
        }
        video.isNeedOrientationUtils = false
        if(binding.viewpager.currentItem==position){
            video.startPlayLogic()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        GSYVideoManager.releaseAllVideos();
    }

}