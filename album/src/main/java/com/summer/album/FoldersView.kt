package com.summer.album

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.bumptech.glide.Glide
import com.cmccit.basemodule.customview.lifecycle.LifecycleRecyclerView
import com.summer.album.databinding.FolderBinding
import com.summer.database.album.Folder
import com.summer.lib.activity.BaseViewModel
import com.summer.module.album.AlbumActivity
import com.summer.module.album.AlbumViewModel

class FoldersView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LifecycleRecyclerView(context, attrs) {
    var folder:Folder? = null
    init {
        setBackgroundResource(com.summer.view.R.color.color_ffffff)
        setPadding(0,SizeUtils.dp2px(20f),0,0)
        layoutManager = GridLayoutManager(context,5)
        class MyViewHolder(itemView: View) : ViewHolder(itemView) {
            fun setData(parentFolder:Folder?,currentfolder: Folder?,position:Int){
                val bind = FolderBinding.bind(itemView)
                bind.root.layoutParams.height = ScreenUtils.getScreenWidth()/5
                bind.image.visibility = INVISIBLE
                bind.text.visibility = INVISIBLE
                if(currentfolder?.item!=null){
                    bind.image.visibility = VISIBLE
                    Glide.with(context).load(AlbumUrlTool.getImageThumbnailUrl(currentfolder?.item!!.netpath,currentfolder?.item!!)).into(bind.image)
                    bind.image.setOnClickListener{

                        parentFolder?.items?.let {
                            val albumsView = AlbumsView(context)
                            albumsView.setData(it,position)
                            (context as AlbumActivity).albumView.addView(albumsView,LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
                        }
                    }
                }else{
                    bind.text.visibility = VISIBLE
                    bind.text.text = currentfolder?.name
                    bind.text.setOnClickListener {
                        val foldersView = FoldersView(context)
                        (context as AlbumActivity).albumView.addView(foldersView,LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
                        foldersView.visibility = VISIBLE
                        foldersView.setData(currentfolder)
                    }
                }
            }
        }
        val myAdapter = object : RecyclerView.Adapter<MyViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                    return MyViewHolder(FolderBinding.inflate(LayoutInflater.from(context),parent,false).root)
            }

            override fun getItemCount(): Int {
                return folder?.folderList?.size?:0
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.setData(folder,folder?.folderList?.getOrNull(position),position)
            }

        }
        adapter = myAdapter
    }
    fun setData(folder:Folder?){
        this.folder = folder
        adapter?.notifyDataSetChanged()
    }
}