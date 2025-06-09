package com.summer.module.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.summer.database.album.Folder
import com.summer.database.album.Item
import com.summer.lib.activity.BaseViewModel
import com.summer.lib.activity.getViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlbumViewModel: BaseViewModel() {

    val items = MutableLiveData<MutableList<Item>>()
    val positionOfAlbum = MutableLiveData<Int>()
    val positionOfMonthAlbum = MutableLiveData<Int>()
    val rootFolder = MutableLiveData(Folder("根目录", 0,null,mutableMapOf()))

    fun 处理日期(items:MutableList<Item>){
        val day = 1000*60*60*24
        var lastDay = 0L
        val size = 5
        var count = 0
        val dayList = mutableListOf<Item>()
        while (count<items.size){
            val item = items[count]
            item.empty = 1
            val d = item.ctime/day
            if(d!=lastDay){//第一个新日期
                if(count!=0){//第一个上面没有
                    val restCount = dayList.size%size
                    if(restCount!=0){
                        var emptyCount = size - restCount
                        items[count-1].empty = emptyCount+1//自己也占据一个位置
                    }
                }
                for(num in 0 until if(dayList.size>=size) size else dayList.size){
                    dayList[num].top = 2
                }
                if(count!=0){
                    dayList[0].top = 1
                }else{
                    items[0].top = 1
                }
                dayList.clear()//清空上个日期的总和
            }
            dayList.add(item)
            lastDay = d
            ++count
        }
        this.items.postValue(items)
    }

    fun 处理文件夹(items:MutableList<Item>){
        ///storage/emulated/0
        ///storage/emulated/0/tencent/MicroMsg/WeChat/1553227515013.mp4
        items.forEach {item->
            item.locpath?.also {locpath->
                val lists = locpath.replace("/storage/emulated/0/","").split("/").toMutableList()
                var currentFolder = rootFolder.value!!
                lists.forEachIndexed { index, s ->
                    if(index!=lists.size-1){
                        if(currentFolder.folders==null){
                            currentFolder.folders = mutableMapOf()
                        }
                        if(!currentFolder.folders!!.containsKey("${s}:0")){
                            currentFolder.folders!!["${s}:0"] = Folder(s,0, null,mutableMapOf())
                        }
                        currentFolder = currentFolder.folders!!["${s}:0"]!!
                    }else{
                        if(currentFolder.folders==null){
                            currentFolder.folders = mutableMapOf()
                        }
                        currentFolder.folders!!["${s}:1"]= Folder(s,1, item,null)
                    }
                }
            }
        }
        fun 递归修改(folder: Folder){
            folder.folderList = folder.folders?.values?.toMutableList()
            folder.folderList?.forEachIndexed { index, f ->
                f.item?.let {item->
                    if(folder.items==null){
                        folder.items = mutableListOf()
                    }
                    item.position = index
                    folder.items?.add(item)
                }
            }
            folder.folders?.forEach { t, f ->
                递归修改(f)
            }
        }
        递归修改(rootFolder.value!!)
        //LogUtils.e(rootFolder)
        rootFolder.also {
            it.postValue(it.value)
        }
    }
}