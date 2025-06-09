package com.summer.album

import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.summer.database.album.Item
import com.summer.lib.activity.BaseActivity
import com.summer.lib.activity.BaseViewModel
import com.summer.lib.activity.getViewModel
import com.summer.module.album.AlbumViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDBViewModel:BaseViewModel() {

    fun insert(items:List<Item>){
        viewModelScope.launch(Dispatchers.IO) {
            var time = System.currentTimeMillis()
            AlbumApplication.database.itemDao().let {dao->
                items.forEach {item->
                    dao.insert(item)
                }
            }
            LogUtils.e(System.currentTimeMillis() - time)//65888
        }
    }
    fun getAll(activity: BaseActivity){
        viewModelScope.launch(Dispatchers.IO) {
            var time = System.currentTimeMillis()
            AlbumApplication.database.itemDao().let {dao->
                dao.getAll().also {list->
                    activity.getViewModel<AlbumViewModel>().处理日期(list)
                    activity.getViewModel<AlbumViewModel>().处理文件夹(list)
                }
            }
        }
    }
}