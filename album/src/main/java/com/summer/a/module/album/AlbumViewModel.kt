package com.summer.a.module.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.summer.a.network.album.AlbumNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumViewModel: ViewModel() {

    companion object{
        fun instance(viewModelStoreOwner:ViewModelStoreOwner):AlbumViewModel{
            return ViewModelProvider(viewModelStoreOwner)[AlbumViewModel::class.java]
        }
    }

    fun getData(){
        viewModelScope.launch(Dispatchers.Default) {
            var pictures = AlbumNetWork.getAllPictures()
            LogUtils.e(GsonUtils.toJson(pictures))
        }

    }


}